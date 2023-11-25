package com.rup.map

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMapBinding

class MapActivity : BaseBindingActivity<ActivityMapBinding, MapViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun setup() {
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