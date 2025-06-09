package com.example.clothesbyweather.data.remote.entity.response

import com.example.clothesbyweather.domain.entity.Home
import com.example.clothesbyweather.domain.entity.HomeWeather
import kotlin.Int

data class HomeResponse(val response: WeatherResponse) {
    fun toHome(): Home =
        Home(weatherList = this.response.body.items.toWeatherList())
}
data class WeatherResponse(val header: WeatherHeader, val body: WeatherBody)
data class WeatherHeader(val resultCode : Int, val resultMsg : String)
data class WeatherBody(val dataType : String, val items : WeatherItems, val totalCount : Int)

data class WeatherItems(val item : ArrayList<WeatherItem>) {
    fun toWeatherList(): ArrayList<HomeWeather> {
        val weatherGroup = item.groupBy{ it.fcstTime }
        var weatherList = arrayListOf<HomeWeather>()
        weatherGroup.forEach { group ->
            var pty = 0
            var sky = 0
            var temperature = 0
            var humidity = 0
            var precipitation = 0
            group.value.forEach { weather ->
                when(weather.category) {
                    // 1시간 기온
                    CategoryType.TMP -> temperature = weather.fcstValue.toInt()
                    // 강수 확률
                    CategoryType.POP -> precipitation = weather.fcstValue.toInt()
                    // 강수 형태
                    CategoryType.PTY -> pty = weather.fcstValue.toInt()
                    // 습도
                    CategoryType.REH -> humidity = weather.fcstValue.toInt()
                    // 하늘 상태
                    CategoryType.SKY -> sky = weather.fcstValue.toInt()
                    else -> {}
                }
            }
            weatherList += HomeWeather(
                pty = pty,
                sky = sky,
                fcstDate = group.value[0].fcstDate,
                fcstTime = group.value[0].fcstTime,
                temperature = temperature,
                humidity = humidity,
                precipitation = precipitation
            )
        }
        return weatherList
    }
}

data class WeatherItem(
    val category: CategoryType,
    val fcstDate: String,
    val fcstTime: String,
    val fcstValue: String,
)

enum class CategoryType {
    TMP, // 1시간 기온
    POP, // 강수 확률
    PTY, // 강수 형태
    REH, // 습도
    SKY, // 하늘 상태

    PCP, // 1시간 강수량
    SNO, // 1시간 신적설
    TMN, // 일 최저 기온
    TMX, // 일 최고 기온
    UUU, // 풍속(동서성분)
    VVV, // 풍속(남북성분)
    WAV, // 파고
    VEC, // 풍향
    WSD // 풍속
}