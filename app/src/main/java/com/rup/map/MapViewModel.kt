package com.rup.map

import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.map.model.MapMarker

class MapViewModel: BaseViewModel() {

    var previousMapMarker = emptyList<MapMarker>()
    private val _mapMakers = MutableLiveData(emptyList<MapMarker>())
    val mapMarker get() = _mapMakers


    fun setMapMarker(){
        _mapMakers.value = listOf(
            MapMarker(
                37.5670135, 126.9783740
            ),
            MapMarker(
                37.5670135, 126.9883740
            ),
            MapMarker(
                37.5570135, 126.9683740
            )
        )
    }
}