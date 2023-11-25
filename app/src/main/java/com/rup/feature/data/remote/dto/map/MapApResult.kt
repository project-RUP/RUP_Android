package com.rup.feature.data.remote.dto.map

import com.google.gson.annotations.SerializedName

data class MapApResult(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<MapApiMarker>
){
    fun getMarkers() = result.map {
        it.getMarker()
    }
}