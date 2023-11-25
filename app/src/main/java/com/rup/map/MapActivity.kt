package com.rup.map

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMapBinding

class MapActivity : BaseBindingActivity<ActivityMapBinding, MapViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun setup() {
        viewModel.mapMarker
            .map {
                viewModel.previousMapMarker to it
            }
            .observe(this)
            { (previousMapMarker, currentMarker) ->
                previousMapMarker.forEach {
                    it.removeMapMarker()
                }
                binding.map.getMapAsync { naverMap ->
                    currentMarker.forEach {
                        it.build(this, naverMap)
                    }
                }
            }
        viewModel.setMapMarker()
    }

    override val viewModel: MapViewModel
        get() = ViewModelProvider(this).get(MapViewModel::class.java)
}