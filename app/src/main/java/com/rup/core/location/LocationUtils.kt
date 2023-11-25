package com.rup.core.location

import android.location.Location
import com.naver.maps.geometry.LatLng
import com.rup.feature.presentation.map.model.MapMarker

class LocationUtils {
    companion object {
        fun calculateCenter(mapMarkers: List<MapMarker>): LatLng {
            if (mapMarkers.isEmpty()) {
                return LatLng(37.5559, 126.9723)
            }

            var sumLat = 0.0
            var sumLng = 0.0

            for (mapMarker in mapMarkers) {
                sumLat += mapMarker.lat
                sumLng += mapMarker.lng
            }

            val centerLat = sumLat / mapMarkers.size
            val centerLng = sumLng / mapMarkers.size

            return LatLng(centerLat, centerLng)
        }
    }
}