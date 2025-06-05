package com.example.clothesbyweather.data.remote.repository

import android.util.Log
import com.example.clothesbyweather.data.remote.datasource.HomeDataSource
import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.data.remote.entity.response.HomeResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource
) {
    suspend fun getHome(request: HomeRequest): Result<HomeResponse> =
        kotlin.runCatching {
            homeDataSource.getHome(request)
        }.map { response ->
            Log.d("mmm WeatherRepository", response.toString())
            Log.d("mmm WeatherRepository", response.body()?.response.toString())
            response.body()!!
        }

}