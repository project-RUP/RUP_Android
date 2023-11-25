package com.rup.feature.presentation.login.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.rup.core.base.BaseFragment
import com.rup.databinding.FragmentKakaoLoginBinding
import com.rup.feature.data.remote.dto.user.MemberExistReq

class KakaoLoginFragment : BaseFragment<FragmentKakaoLoginBinding,KakaoLoginViewModel>() {
    override val bindingInflater: (LayoutInflater) -> FragmentKakaoLoginBinding
        get() = FragmentKakaoLoginBinding::inflate

    override val viewModel: KakaoLoginViewModel
        get() = ViewModelProvider(this).get(KakaoLoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val req = MemberExistReq("12345")
        viewModel.existCheck(req)

        binding.loginBtn.setOnClickListener {
            viewModel.onButtonClick(requireContext())
        }
        viewModel.idLiveData.observe(this) { id ->
            // id 값이 변경될 때마다 호출되는 부분
            // UI를 업데이트하거나 필요한 작업을 수행할 수 있음
            Log.d("KakaoLoginFragment",id)
            val req = MemberExistReq(id)
            viewModel.existCheck(req)
        }

        binding.check.setOnClickListener {
            viewModel.test()
        }
    }
}