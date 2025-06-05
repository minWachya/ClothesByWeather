package com.example.clothesbyweather.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.data.remote.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
//    private var _weatherList = getWeather().toMutableStateList()
//    val weatherList: List<WeatherEntity> get() = _weatherList


    // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
    fun getHome() = viewModelScope.launch {
        homeRepository.getHome(HomeRequest(60, 1, "JSON", "20250604", "1700", 55, 127))
            .onSuccess {
                Log.d("mmm", "성공")
                Log.d("mmm", it.toString())
            }.onFailure {
                Log.d("mmm", it.message?: "TT")
                Log.d("mmm", "??")
            }
    }
}