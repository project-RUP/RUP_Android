package com.rup.feature.presentation.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.Main.model.maindto

class MainPageViewModel: BaseViewModel() {
    // LiveData for Maindto list
    private val _Maindtos = MutableLiveData<List<maindto>>()
    val Maindtos: LiveData<List<maindto>> get() = _Maindtos

    // Initialize Maindto list
    init {
        setMaindtos()
    }

    // Set Maindto list
    private fun setMaindtos() {
        _Maindtos.value = listOf(
            maindto("title1", "subscription1", "time1", "amPm1", "headCount1", "location1", "price1"),
            maindto("title2", "subscription2", "time2", "amPm2", "headCount2", "location2", "price2"),
            maindto("title3", "subscription3", "time3", "amPm3", "headCount3", "location3", "price3")
        )
    }
}