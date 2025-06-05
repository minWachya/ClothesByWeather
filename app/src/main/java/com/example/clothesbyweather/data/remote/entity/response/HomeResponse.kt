package com.example.clothesbyweather.data.remote.entity.response

data class HomeResponse(val response: WeatherResponse)
data class WeatherResponse(val header: WeatherHeader, val body: WeatherBody)
data class WeatherHeader(val resultCode : Int, val resultMsg : String)
data class WeatherBody(val dataType : String, val items : WeatherItems, val totalCount : Int)
data class WeatherItems(val item : List<WeatherEntity>)
data class WeatherEntity(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val fcstDate: String,
    val fcstTime: String,
    val fcstValue: String,
    val nx: Int,
    val ny: Int
)