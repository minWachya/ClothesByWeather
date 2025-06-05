package com.example.clothesbyweather.data.remote.service

import com.example.clothesbyweather.data.remote.entity.response.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("getVilageFcst?serviceKey=zDDR5SlQcxjP8%2BKOJn1fWiI1p6aU3XGaeplQAyw%2FtFql6yge3CMoC3VcC2w94fxDCzgLNKyreLOBZtUgz1cq%2Bg%3D%3D")
    suspend fun getHome(
        @Query("numOfRows") numOfRows : Int,    // 한 페이지 경과 수
        @Query("pageNo") pageNo : Int,          // 페이지 번호
        @Query("dataType") dataType : String,   // 응답 자료 형식
        @Query("base_date") baseDate : String,  // 발표 일자
        @Query("base_time") baseTime : String,  // 발표 시각
        @Query("nx") nx : Int,                  // 예보 지점 X 좌표
        @Query("ny") ny : Int                   // 예보 지점 Y 좌표
    ): Response<HomeResponse>
}