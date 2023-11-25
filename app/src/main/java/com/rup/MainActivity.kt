package com.rup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseActivity
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainVIewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup() {
    }

    override val viewModel: MainVIewModel
        get() = ViewModelProvider(this).get(MainVIewModel::class.java)
}