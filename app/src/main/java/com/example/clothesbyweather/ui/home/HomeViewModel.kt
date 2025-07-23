package com.example.clothesbyweather.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.data.remote.repository.HomeRepositoryImpl
import com.example.clothesbyweather.domain.entity.HomeWeather
import com.example.clothesbyweather.util.CoordinateConverter
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val homeRepository: HomeRepositoryImpl,
) : AndroidViewModel(application) {
    private val _weatherList = MutableStateFlow<List<HomeWeather>>(emptyList())
    val weatherList: StateFlow<List<HomeWeather>> = _weatherList.asStateFlow()
    private val _curTemperature = MutableStateFlow<Int>(0)
    val curTemperature: StateFlow<Int> = _curTemperature.asStateFlow()
    private val _curWeather = MutableStateFlow<String>("")
    val curWeather: StateFlow<String> = _curWeather.asStateFlow()
    private val _clothesByWeather = MutableStateFlow<String>("")
    val clothesByWeather: StateFlow<String> = _clothesByWeather.asStateFlow()
    private var baseDate: String
    private var baseTime: String

    init {
        val cal = Calendar.getInstance()
        val cur = Locale.getDefault()
        baseDate = SimpleDateFormat("yyyyMMdd", cur).format(cal.time)
        val hour = SimpleDateFormat("HH", cur).format(cal.time)
        baseTime = getBaseTime(hour.toInt())

        getLocation()
    }


    // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
    private fun getHome(baseDate: String, baseTime: String, nx: Int, ny: Int) = viewModelScope.launch {
        homeRepository.getHome(HomeRequest(150, 1, "JSON", baseDate, baseTime, nx, ny))
            .onSuccess {
                _weatherList.value = it.weatherList
                _curTemperature.value = it.weatherList[0].temperature
                _curWeather.value = getCurWeather(it.weatherList[0].weatherEmoji)
                _clothesByWeather.value = getClothesByWeather(it.weatherList[0].temperature)
            }.onFailure {
                Log.d("mmm HomeViewModel", it.message?: "api 연결 실패")
            }
    }

    // Base_time : 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
    private fun getBaseTime(hour: Int): String =  when {
        hour < 2 -> "2300"
        hour < 5 -> "0200"
        hour < 8 -> "0500"
        hour < 11 -> "0800"
        hour < 14 -> "1100"
        hour < 17 -> "1400"
        hour < 20 -> "1700"
        else -> "2000"
    }

    private fun getCurWeather(weatherEmoji: String): String =  when(weatherEmoji) {
        "🌧" -> "비/소나기"
        "🌨" -> "비/눈"
        "❄" -> "눈"
        "☀" -> "맑음"
        "🌤" -> "구름 많음"
        else -> "흐림"
    }

    private fun getClothesByWeather(temperature: Int): String = when {
        temperature <= 4 -> "패딩, 두꺼운 코트, 목도리, 기모 제품"
        temperature <= 8 -> "코트, 가죽 자켓, 히트텍, 니트"
        temperature <= 11 -> "자켓, 트렌치 코트, 니트, 청바지"
        temperature <= 16 -> "자켓, 가디건, 청바지, 면바지"
        temperature <= 19 -> "얇은 니트, 맨투맨, 가디건, 청바지"
        temperature <= 22 -> "얇은 가디건, 긴팔, 면바지, 청바지"
        temperature <= 27 -> "반팔, 얇은 셔츠, 반바지, 면바지"
        else -> "민소매, 반팔, 반바지, 원피스"
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application.applicationContext)
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken = CancellationTokenSource().token
            override fun isCancellationRequested(): Boolean = false
        }).addOnSuccessListener { location ->
            location?.let {
                val place = CoordinateConverter().convertToXy(lat = it.latitude, lon = it.longitude)
                getHome(baseDate, baseTime, place.nx, place.ny)
            }
        }
            .addOnFailureListener { exception ->
                Log.d("mmm plae", exception.message.toString())
            }
    }
}