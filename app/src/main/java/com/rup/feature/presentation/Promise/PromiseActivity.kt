package com.rup.feature.presentation.Promise

import android.app.TimePickerDialog
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityPromiseBinding
import java.util.Calendar

class PromiseActivity : BaseBindingActivity<ActivityPromiseBinding, PromiseViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPromiseBinding
        get() = ActivityPromiseBinding::inflate
    override val viewModel: PromiseViewModel
        get() = ViewModelProvider(this).get(PromiseViewModel::class.java)

    override fun setup() {
        binding.timeText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // TimePickerDialog 생성
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // 시간이 선택되었을 때의 동작
                    val selectedTime = "$hourOfDay : $minute"
                    // 여기에 선택된 시간에 대한 동작을 추가하세요.
                },
                hour,
                minute,
                false // 24시간 형식으로 표시할지 여부
            )
        }
    }
}