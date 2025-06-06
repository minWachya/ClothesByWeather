package com.example.clothesbyweather.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.data.remote.repository.HomeRepositoryImpl
import com.example.clothesbyweather.domain.entity.HomeWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepositoryImpl,
) : ViewModel() {
    private val _weatherList = MutableStateFlow<List<HomeWeather>>(emptyList())
    val weatherList: StateFlow<List<HomeWeather>> = _weatherList.asStateFlow()

    init {
        getHome()
    }


    // (한 페이지 결과 수 = 60, 페이지 번호 = 1, 응답 자료 형식-"JSON", 발표 날싸, 발표 시각, 예보지점 좌표)
    fun getHome() = viewModelScope.launch {
        homeRepository.getHome(HomeRequest(60, 1, "JSON", "20250606", "1100", 55, 127))
            .onSuccess {
                Log.d("mmm HomeViewModel", "api 연결 성공")
                _weatherList.value = it.weatherList
            }.onFailure {
                Log.d("mmm HomeViewModel", it.message?: "api 연결 실패")
            }
    }
}