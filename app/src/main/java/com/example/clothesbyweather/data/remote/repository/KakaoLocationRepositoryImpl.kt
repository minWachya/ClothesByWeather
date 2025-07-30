package com.example.clothesbyweather.data.remote.repository

import android.util.Log
import com.example.clothesbyweather.data.remote.datasource.KakaoLocationDataSource
import com.example.clothesbyweather.domain.entity.PlaceList
import com.example.clothesbyweather.domain.repository.KakaoLocationRepository
import javax.inject.Inject

class KakaoLocationRepositoryImpl @Inject constructor(
    private val kakaoLocationDataSource: KakaoLocationDataSource
): KakaoLocationRepository {
    override suspend fun getKakaoLocation(x: String, y: String): Result<PlaceList> = runCatching {
        kakaoLocationDataSource.getKakaoLocation(x, y)
    }.map { response ->
        Log.d("mmm??", response.body().toString())
        Log.d("mmm??", response.message())
        Log.d("mmm??", response.toString())
        response.body()!!.toPlaceList()
    }

}