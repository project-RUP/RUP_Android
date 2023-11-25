package com.rup.map

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMapBinding

class MapActivity : BaseBindingActivity<ActivityMapBinding, MapViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMapBinding
        get() = ActivityMapBinding::inflate

    override fun setup() {
    }

    override val viewModel: MapViewModel
        get() = ViewModelProvider(this).get(MapViewModel::class.java)
}