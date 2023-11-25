package com.rup.feature.presentation.Promise

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityPromiseBinding

class PromiseActivity : BaseBindingActivity<ActivityPromiseBinding, PromiseViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPromiseBinding
        get() = ActivityPromiseBinding::inflate
    override val viewModel: PromiseViewModel
        get() = ViewModelProvider(this).get(PromiseViewModel::class.java)

    override fun setup() {
    }
}