package com.example.clothesbyweather.domain.entity

data class Home(val weatherList : ArrayList<HomeWeather>)

data class HomeWeather(
    private val pty: Int,
    private val sky: Int,
    private val fcstDate: String,
    private val fcstTime: String,
    val temperature: Int,
    val humidity: Int,
    val precipitation: Int
) {
    val weatherEmoji: String
        get() =
            // PTY: 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
            if(pty != 0) when(pty) {
                1 -> "🌧"
                2 -> "🌨"
                3 -> "❄"
                else -> "🌧"
            }
            // SKY: 맑음(1), 구름많음(3), 흐림(4)
            else when(sky) {
                1 -> "☀"
                3 -> "🌤"
                else -> "☁"
            }

    val time: String
        get() {
            val hour = fcstTime.substring(0..1).toInt()
            return if(hour < 12) "오전 ${hour}시" else "오후 ${hour-12}시"
        }
}