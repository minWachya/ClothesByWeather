package com.example.clothesbyweather.data.remote.repository

import android.util.Log
import com.example.clothesbyweather.data.remote.datasource.HomeDataSource
import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.domain.entity.Home
import com.example.clothesbyweather.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
): HomeRepository {
    override suspend fun getHome(request: HomeRequest): Result<Home> = kotlin.runCatching {
        homeDataSource.getHome(request)
    }.map { response ->
        response.body()!!.toHome()
    }

}