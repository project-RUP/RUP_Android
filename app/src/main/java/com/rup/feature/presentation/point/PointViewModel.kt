package com.rup.feature.presentation.point

import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.point.model.PointHistoryItem

class PointViewModel : BaseViewModel() {

    private val _pointHistory = MutableLiveData(emptyList<PointHistoryItem>())
    val pointHistory get() = _pointHistory

    fun getPointHistory() {
        val newPointHistoryItem = listOf(
            PointHistoryItem(
                "+2000",
                "레온PC방",
                "상강사거리",
                "2024.11.26",
            ),
            PointHistoryItem(
                "+4000",
                "레온PC방",
                "상강사거리",
                "2024.11.25",
            ),
            PointHistoryItem(
                "+1000",
                "레온PC방",
                "상강사거리",
                "2024.11.24",
            ), PointHistoryItem(
                "+500",
                "레온PC방",
                "상강사거리",
                "2024.11.23",
            )
        )
        _pointHistory.value = newPointHistoryItem
    }
}