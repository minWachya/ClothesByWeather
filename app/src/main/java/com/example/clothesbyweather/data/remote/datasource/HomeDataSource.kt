package com.example.clothesbyweather.data.remote.datasource

import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.data.remote.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService
) {
    suspend fun getHome(request: HomeRequest)
    = homeService.getHome(request.numOfRows,
        request.pageNo,
        request.dataType,
        request.baseDate,
        request.baseTime,
        request.nx,
        request.ny
    )
}