package com.rup.feature.data.remote.dto.map

import com.google.gson.annotations.SerializedName
import com.rup.feature.presentation.map.model.MapMarker

data class MapApiMarker(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    fun getMarker() =
        MapMarker(
            latitude.toDouble(),
            longitude.toDouble(),
            url
        )
}