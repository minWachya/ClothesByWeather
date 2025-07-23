package com.example.clothesbyweather.data.remote.entity.response

import com.example.clothesbyweather.domain.entity.PlaceList
import com.example.clothesbyweather.domain.entity.PlaceLocation
import com.example.clothesbyweather.domain.entity.PlaceName
import com.google.gson.annotations.SerializedName


data class KakaoLocationResponse(val documents: ArrayList<KakaoDocument>) {
    fun toPlaceList(): PlaceList {
        val placeList = arrayListOf<PlaceLocation>()
        documents.forEach {
            placeList += it.toPlaceLocation()
        }
        return PlaceList(locations = placeList)
    }
}

data class KakaoDocument(
    val address: KakaoAddress,
) {
    fun toPlaceLocation(): PlaceLocation = PlaceLocation(address = address.toPlaceName())
}

data class KakaoAddress(
    @SerializedName("address_name") val addressName: String,
    @SerializedName("region_1depth_name") val region1: String,
    @SerializedName("region_2depth_name") val region2: String,
    @SerializedName("region_3depth_name") val region3: String,
    ) {
    fun toPlaceName(): PlaceName = PlaceName(name = region3)
}