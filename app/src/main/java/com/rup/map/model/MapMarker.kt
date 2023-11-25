package com.rup.map.model

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker

data class MapMarker(
    val lat: Double,
    val lng: Double,
){
    private val marker = Marker()

    fun build(naverMap: NaverMap){
        marker.position = LatLng(lat, lng)
        marker.map = naverMap
    }

    fun removeMapMarker(){
        marker.map = null
    }
}
