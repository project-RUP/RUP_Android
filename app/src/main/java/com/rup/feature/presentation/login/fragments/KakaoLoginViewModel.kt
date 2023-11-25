package com.rup.feature.presentation.login.fragments

import MemberExistRes
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.kakao.sdk.user.UserApiClient
import com.rup.RUPApplication.Companion.context
import com.rup.core.base.BaseViewModel
import com.rup.core.util.AccountHelper
import com.rup.di.AppModule
import com.rup.di.AuthModule
import com.rup.feature.data.remote.UserAuthApi
import com.rup.feature.data.remote.dto.user.LoginRes
import com.rup.feature.data.remote.dto.user.MemberExistReq
import com.rup.network.RetrofitClient
import kotlinx.coroutines.launch

class KakaoLoginViewModel : BaseViewModel() {
    var retrofitInterface: UserAuthApi
    var retrofitInterface2: RetrofitClient

    private val _result2 = MutableLiveData<String?>()
    val result2: LiveData<String?>
        get() = _result2

    val kakaoLoginRepository = KakaoLoginRepository()

    val idLiveData: MutableLiveData<String> = MutableLiveData()

    private val _buttonClicked = MutableLiveData<Boolean>()
    val buttonClicked: LiveData<Boolean> = _buttonClicked


    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?>
        get() = _result

    init {
        retrofitInterface = AuthModule.getApiService()
        retrofitInterface2 = AppModule.getApiService()

    }

    fun getInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KakaoLoginRepository", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                val id = user.id.toString()
                Log.i("KakaoLoginRepository", "사용자 정보 요청 성공\n회원번호: $id")
                idLiveData.postValue(id) // LiveData에 값 설정
            }
        }
    }

    // 버튼 클릭 함수 정의
    fun onButtonClick(context: Context) {
        _buttonClicked.value = true // 클릭 시 LiveData 업데이트
        val success = kakaoLoginRepository.loginWithKakao(context)
        Log.d("onButtonClick", success.toString())
        if (success) {
            getInfo()
        }
    }

    fun existCheck(id: MemberExistReq) = viewModelScope.launch {
        val gson = Gson()
        val response: MemberExistRes =
            gson.fromJson(retrofitInterface.existCheck(id).toString(), MemberExistRes::class.java)
        val exist = response.result.exist
        Log.d("response", exist.toString())
        if (exist) {
            userLogin(id)

        } else {
            // 회원가입
        }
    }

    fun userLogin(id: MemberExistReq) = viewModelScope.launch {
        val gson = Gson()
        val response: LoginRes =
            gson.fromJson(retrofitInterface.userLogin(id).toString(), LoginRes::class.java)
        val accessToken = response.result.accessToken
        AccountHelper.setAuthToken(accessToken.toString())
        val sharedPref = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)

        sharedPref?.edit()?.putString("ACCESS_TOKEN", accessToken)?.apply()
    }


    fun test() = viewModelScope.launch {
        val gson = Gson()

        _result.value = retrofitInterface2.getRetrofit().toString()
        val response = gson.fromJson(result2.value, JsonElement::class.java)

    }

}
