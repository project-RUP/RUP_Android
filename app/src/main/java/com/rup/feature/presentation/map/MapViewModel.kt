package com.rup.feature.presentation.map

import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.map.model.MapMarker

class MapViewModel: BaseViewModel() {

    private lateinit var reservationId: String
    var previousMapMarker = emptyList<MapMarker>()

    private val _mapMakers = MutableLiveData(emptyList<MapMarker>())
    val mapMarker get() = _mapMakers

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
    }

    fun setReservationId(id: String){
        reservationId = id
    }
}