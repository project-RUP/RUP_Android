package com.rup.feature.presentation.MainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.R
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMyPageBinding

class MyPageActivity:BaseBindingActivity<ActivityMyPageBinding,MyPageViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMyPageBinding=ActivityMyPageBinding::inflate

    override fun setup() {


        viewModel.userProfile.observe(this) { userProfile ->
            binding.name.text = userProfile.name
            binding.deposit.text = userProfile.deposit.toString()

            //binding.profileImage.text=userProfile.profileImage
            // 필요한 경우 다른 프로퍼티도 바인딩하세요.
        }
    }

    override val viewModel: MyPageViewModel
        get() = ViewModelProvider(this).get(MyPageViewModel::class.java)
}