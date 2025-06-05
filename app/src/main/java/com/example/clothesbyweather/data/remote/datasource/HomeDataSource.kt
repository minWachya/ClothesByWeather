package com.example.clothesbyweather.data.remote.datasource

import com.example.clothesbyweather.data.remote.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService
) {
    suspend fun getHome(numOfRows : Int,
                           pageNo : Int,
                           dataType : String,
                           baseDate : String,
                           baseTime : String,
                           nx : Int,
                           ny : Int )
    = homeService.getHome(numOfRows,
        pageNo,
        dataType,
        baseDate,
        baseTime,
        nx,
        ny
    )
}