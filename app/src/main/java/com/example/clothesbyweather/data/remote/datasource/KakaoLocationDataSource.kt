package com.example.clothesbyweather.data.remote.datasource

import com.example.clothesbyweather.data.remote.service.KakaoLocationService
import javax.inject.Inject

class KakaoLocationDataSource @Inject constructor(
    private val kakaoLocationService: KakaoLocationService
) {
    suspend fun getKakaoLocation(x: String, y: String)
    = kakaoLocationService.getKakaoLocation(x, y)
}