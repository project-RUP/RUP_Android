package com.rup.feature.presentation.point

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.R
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityPointBinding

class PointActivity : BaseBindingActivity<ActivityPointBinding, PointViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPointBinding
        get() = ActivityPointBinding::inflate

    override val viewModel: PointViewModel
        get() = ViewModelProvider(this).get(PointViewModel::class.java)

    override fun setup() {
    }
}