package com.rup.feature.presentation.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.Main.model.maindto

class MainPageViewModel : BaseViewModel() {
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

            maindto("김민지와 아이들", "2023.11.30", "7:00", "PM", "headCount1", "종로", "price1","00000"),
            maindto("삼겹살 모임", "2023.12.15", "5:00", "PM", "headCount2", "강남", "price3","00000") ,
            maindto("풋살 ", "2023.12.19", "1:00", "PM", "headCount2", "강남", "price3","00000")


       
        )
    }
}