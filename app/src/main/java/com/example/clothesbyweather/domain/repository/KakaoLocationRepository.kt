package com.example.clothesbyweather.domain.repository

import com.example.clothesbyweather.domain.entity.PlaceList

interface KakaoLocationRepository {
    suspend fun getKakaoLocation(x: String, y: String): Result<PlaceList>
}