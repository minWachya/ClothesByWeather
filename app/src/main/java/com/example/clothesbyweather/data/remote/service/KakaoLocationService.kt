package com.example.clothesbyweather.data.remote.service

import com.example.clothesbyweather.data.remote.entity.response.KakaoLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoLocationService {
    @GET("local/geo/coord2address.json")
    suspend fun getKakaoLocation(
        @Query("x") x : String,    // 경도
        @Query("y") y : String,    // 위도
    ): Response<KakaoLocationResponse>
}