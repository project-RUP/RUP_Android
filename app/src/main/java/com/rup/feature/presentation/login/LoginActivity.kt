package com.rup.feature.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.rup.core.util.AccountHelper
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityLoginBinding
import com.rup.feature.data.remote.dto.user.MemberExistReq

class LoginActivity : BaseBindingActivity<ActivityLoginBinding, LoginViewModel>() {

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun setup() {
    }

    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this).get(LoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        AccountHelper.initAccountManager(this)

        val accountId = "id"
        val accountPw = "password"
        AccountHelper.addAccount(accountId, accountPw)

        binding.loginBtn.setOnClickListener {
            viewModel.onButtonClick(this)
        }
        viewModel.idLiveData.observe(this) { id ->
            // id 값이 변경될 때마다 호출되는 부분
            // UI를 업데이트하거나 필요한 작업을 수행할 수 있음
            Log.d("KakaoLoginFragment",id)
            val req = MemberExistReq(id)
            viewModel.existCheck(req)
        }
        viewModel.navigateToOtherScreen.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                // 화면 전환 로직을 이곳에 구현
                val intent = Intent(this, NicknameActivity::class.java)
                startActivity(intent)

                // 화면 전환 이벤트 처리 (값 변경 후 초기화)
                viewModel
            }
        }
    }
}