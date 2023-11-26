package com.rup.feature.presentation.login


import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.MainActivity
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityNicknameBinding
import com.rup.feature.presentation.Main.MainPageActivity
import com.rup.feature.presentation.MainPage.MyPageActivity
import kotlinx.coroutines.MainScope

class NicknameActivity : BaseBindingActivity<ActivityNicknameBinding, NicknameViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityNicknameBinding
        get() = ActivityNicknameBinding::inflate

    override val viewModel: NicknameViewModel
        get() = ViewModelProvider(this).get(NicknameViewModel::class.java)

    override fun setup() {
        binding.button.setOnClickListener {
            moveIntent(MainPageActivity()::class.java)
        }
    }


}