package com.rup.feature.presentation.map

import androidx.lifecycle.MutableLiveData
import com.naver.maps.geometry.LatLng
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.map.model.MapMarker
import com.rup.feature.presentation.map.model.calculateCenter

class MapViewModel: BaseViewModel() {

    var previousMapMarker = emptyList<MapMarker>()

    private val _mapMakers = MutableLiveData(emptyList<MapMarker>())
    val mapMarker get() = _mapMakers

    private val _cameraAnimation = MutableLiveData<LatLng?>(null)
    val cameraAnnotation get() = _cameraAnimation

    fun setMapMarker(){
        val newMapMarkers = listOf(
            MapMarker(
                37.5670135, 126.9783740
            ),
            MapMarker(
                37.5670135, 126.9883740
            ),
            MapMarker(
                37.5570135, 126.9683740
            ),
            MapMarker(
                37.2570135, 126.7683740
            )
        )
        _mapMakers.value = newMapMarkers
        _cameraAnimation.value = newMapMarkers.calculateCenter()
    }
}