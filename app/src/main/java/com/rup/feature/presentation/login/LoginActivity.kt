package com.rup.feature.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rup.core.util.AccountHelper
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityLoginBinding

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
            //textapi로 넘어오는 string을 token으로 저장
            viewModel.test()
            viewModel.result.observe(this, Observer {
                Log.d("LoginActivity", it.toString())
            })
        }
    }

}