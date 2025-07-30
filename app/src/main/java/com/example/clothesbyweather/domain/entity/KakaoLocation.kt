package com.example.clothesbyweather.domain.entity

data class PlaceList(val locations : ArrayList<PlaceLocation>)

data class PlaceLocation(val address: PlaceName)

data class PlaceName(
    val name: String
)