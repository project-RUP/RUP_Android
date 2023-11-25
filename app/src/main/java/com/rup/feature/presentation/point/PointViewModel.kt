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
                "1",
                "1",
                "1",
                "1",
            ),
            PointHistoryItem(
                "2",
                "2",
                "2",
                "2",
            ),
            PointHistoryItem(
                "3",
                "3",
                "3",
                "3",
            ), PointHistoryItem(
                "4",
                "4",
                "4",
                "4",
            )
        )
        _pointHistory.value = newPointHistoryItem
    }
}