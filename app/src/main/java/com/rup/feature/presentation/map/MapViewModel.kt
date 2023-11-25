package com.rup.feature.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.rup.core.base.BaseViewModel
import com.rup.di.AppModule.gson
import com.rup.feature.data.remote.MapApi
import com.rup.feature.data.remote.dto.map.MapApResult
import com.rup.feature.data.remote.dto.map.MapApiPram
import com.rup.feature.data.remote.dto.user.LoginRes
import com.rup.feature.presentation.map.model.MapMarker
import com.rup.network.NetworkModule
import kotlinx.coroutines.launch

class MapViewModel: BaseViewModel() {

    private lateinit var reservationId: String
    var previousMapMarker = emptyList<MapMarker>()

    private val _mapMakers = MutableLiveData(emptyList<MapMarker>())
    val mapMarker get() = _mapMakers

    private val remote by lazy {
        NetworkModule.getRetrofit().create(MapApi::class.java)
    }

    fun setMapMarker(latLng: LatLng){
        viewModelScope.launch {
            val newMapMarkersJson = remote.promisesLocations(
                "2",
                MapApiPram(
                    latLng.longitude.toString(),
                    latLng.latitude.toString(),
                )
            )
            Log.d("LOGEE", "setMapMarker: $newMapMarkersJson")
            val newMapMarkers = gson.fromJson(newMapMarkersJson, MapApResult::class.java)
            _mapMakers.value = newMapMarkers.getMarkers()
        }
    }

    fun setReservationId(id: String){
        reservationId = id
    }
}