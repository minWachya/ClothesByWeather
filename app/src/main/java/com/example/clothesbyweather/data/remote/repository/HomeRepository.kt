package com.example.clothesbyweather.data.remote.repository

import android.util.Log
import com.example.clothesbyweather.data.remote.datasource.HomeDataSource
import com.example.clothesbyweather.data.remote.entity.response.HomeResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource
) {
    suspend fun getHome(numOfRows : Int,
                           pageNo : Int,
                           dataType : String,
                           baseDate : String,
                           baseTime : String,
                           nx : Int,
                           ny : Int): Result<HomeResponse> =
        kotlin.runCatching {
            homeDataSource.getHome(numOfRows,
                pageNo,
                dataType,
                baseDate,
                baseTime,
                nx,
                ny)
        }.map { response ->
            Log.d("mmm WeatherRepository", response.toString())
            Log.d("mmm WeatherRepository", response.body()?.response.toString())
            response.body()!!
        }

}