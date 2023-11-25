package com.rup

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup() {
    }

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)
}