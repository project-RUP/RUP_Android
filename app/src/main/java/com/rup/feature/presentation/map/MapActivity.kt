package com.rup.feature.presentation.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.rup.R
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

        binding.backArrow.setOnClickListener {
            backScreen()
        }

        binding.avatar1.setImageResource(R.drawable.small_j)
        binding.avatar2.setImageResource(R.drawable.small_p)
        binding.avatar3.setImageResource(R.drawable.pp)

        viewModel.promise.observe(this){
            it?.let {
                val result = it.result
                with(binding){
                    title1.text = result.name
                    date.text = result.promiseTime
                    lateFeeText.text = result.penalty.toString()
                    verificationCode.text = "${result.inviteCode}(인증코드)"
                }
            }
        }

        setNaverMap()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                location?.toLatLng()?.let { viewModel.setMapMarker(it) }
            }
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
            if (currentMarker.isNotEmpty()) {
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

fun Location.toLatLng() = LatLng(
    this.latitude,
    this.longitude,
)