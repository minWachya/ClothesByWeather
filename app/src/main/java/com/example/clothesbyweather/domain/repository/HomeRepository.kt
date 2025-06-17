package com.example.clothesbyweather.domain.repository

import com.example.clothesbyweather.data.remote.entity.request.HomeRequest
import com.example.clothesbyweather.domain.entity.Home

interface HomeRepository {
    suspend fun getHome(request: HomeRequest): Result<Home>
}