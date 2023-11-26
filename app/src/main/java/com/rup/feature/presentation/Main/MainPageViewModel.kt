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

            maindto("1", "민지랑 서현역 놀러가기~~~~~", "2023.11.30", "7:00", "PM", "강남", "강남","00000"),
            maindto("2", "영화보러가기", "2023.12.15", "5:00", "PM", "강남", "강남","00000") ,
            maindto("3 ", "삼겹살 먹기", "2023.12.19", "2:00", "PM", "신촌", "신촌","00000"),
            maindto("3 ", "삼겹살 먹기", "2023.12.19", "2:00", "PM", "신촌", "신촌","00000"),
            maindto("3 ", "삼겹살 먹기", "2023.12.19", "2:00", "PM", "신촌", "신촌","00000")


       
        )
    }
}