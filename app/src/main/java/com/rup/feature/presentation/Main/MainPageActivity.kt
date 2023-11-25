package com.rup.feature.presentation.Main

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMainPageBinding

class MainPageActivity : BaseBindingActivity<ActivityMainPageBinding, MainPageViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainPageBinding = ActivityMainPageBinding::inflate

    override fun setup() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MainRecyclerViewAdapter(emptyList())
        binding.myRecyclerView.adapter = adapter

        // Observe Maindtos LiveData
        viewModel.Maindtos.observe(this) { Maindtos ->
            adapter.updateData(Maindtos)
        }
    }

    override val viewModel: MainPageViewModel
        get() = ViewModelProvider(this).get(MainPageViewModel::class.java)}