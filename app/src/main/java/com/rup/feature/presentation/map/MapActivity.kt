package com.rup.feature.presentation.map

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMapBinding

class MapActivity : BaseBindingActivity<ActivityMapBinding, MapViewModel>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun setup() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.expandIcon.setOnClickListener {
            if (binding.expandedMenu.visibility == View.VISIBLE) {
                binding.expandedMenu.visibility =
                    View.GONE
                binding.expandIcon.animate().setDuration(200).rotation(180f)
            } else {
                binding.expandedMenu.visibility =
                    View.VISIBLE
                binding.expandIcon.animate().setDuration(200).rotation(0f)
            }
        }

        setNaverMap()

        // FIXME: 마커 호출 코드로 변경
        viewModel.setMapMarker()
    }

    private fun setNaverMap() = binding.map.getMapAsync { naverMap ->
        viewModel.mapMarker
            .map {
                viewModel.previousMapMarker to it
            }
            .observe(this)
            { (previousMapMarker, currentMarker) ->
                previousMapMarker.forEach {
                    it.removeMapMarker()
                }

                currentMarker.forEach {
                    it.build(this, naverMap)
                }

            }

        viewModel.cameraAnnotation
            .distinctUntilChanged()
            .observe(this) {
                it?.let { latLng ->
                    CameraUpdate.scrollTo(latLng)
                        .animate(CameraAnimation.Easing, 500)
                        .let { cameraUpdate ->
                            naverMap.moveCamera(cameraUpdate)
                        }
                }
            }
    }

    override val viewModel: MapViewModel
        get() = ViewModelProvider(this).get(MapViewModel::class.java)
}