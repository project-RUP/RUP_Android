package com.rup.feature.presentation.map

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMapBinding
import com.rup.feature.presentation.map.model.toLatLng
import java.io.Serializable

class MapActivity : BaseBindingActivity<ActivityMapBinding, MapViewModel>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun setup() {
        intent.getSerializableExtra(StartArgs.key, StartArgs::class.java)?.let { reservationId ->
            viewModel.setReservationId(reservationId.id)
        } ?: {
            backScreen()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.expandIcon.setOnClickListener {
            if (binding.expandedMenu.visibility == View.VISIBLE) {
                binding.expandedMenu.visibility = View.GONE
                binding.expandIcon.animate().setDuration(200).rotation(0f)
            } else {
                binding.expandedMenu.visibility = View.VISIBLE
                binding.expandIcon.animate().setDuration(200).rotation(180f)
            }
        }

        setNaverMap()

        // FIXME: 마커 호출 코드로 변경
        viewModel.setMapMarker()
    }

    private fun setNaverMap() = binding.map.getMapAsync { naverMap ->
        naverMap.uiSettings.isZoomControlEnabled = false
        viewModel.mapMarker.map {
            viewModel.previousMapMarker to it
        }.observe(this) { (previousMapMarker, currentMarker) ->
            previousMapMarker.forEach {
                it.removeMapMarker()
            }

            currentMarker.forEach {
                it.build(this, naverMap)
            }

            CameraUpdate.fitBounds(
                LatLngBounds.from(
                    currentMarker.map { it.toLatLng() }
                ),
                100
            ).let {
                naverMap.moveCamera(it)
            }

        }
    }

    override val viewModel: MapViewModel
        get() = ViewModelProvider(this).get(MapViewModel::class.java)


    data class StartArgs(
        val id: String
    ) : Serializable {
        companion object {
            val key = "MapActivity-key"
        }
    }
}