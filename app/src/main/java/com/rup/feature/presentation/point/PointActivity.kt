package com.rup.feature.presentation.point

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityPointBinding
import com.rup.feature.presentation.point.adapter.PointAdapter

class PointActivity : BaseBindingActivity<ActivityPointBinding, PointViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPointBinding
        get() = ActivityPointBinding::inflate

    override val viewModel: PointViewModel
        get() = ViewModelProvider(this).get(PointViewModel::class.java)

    private val adapter by lazy {
        PointAdapter(emptyList())
    }
    override fun setup() {
        binding.pointHistory.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.pointHistory.adapter = adapter
    }
}