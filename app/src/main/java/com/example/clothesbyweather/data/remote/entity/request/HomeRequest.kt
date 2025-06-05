package com.example.clothesbyweather.data.remote.entity.request

data class HomeRequest(
    val numOfRows : Int,
    val pageNo : Int,
    val dataType : String,
    val baseDate : String,
    val baseTime : String,
    val nx : Int,
    val ny : Int
)