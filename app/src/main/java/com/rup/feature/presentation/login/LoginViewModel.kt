package com.rup.feature.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParser
import com.rup.core.util.AccountHelper
import com.rup.core.base.BaseViewModel
import com.rup.di.AppModule
import com.rup.network.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel(){
    var retrofitInterface : RetrofitClient

    private val _result = MutableLiveData<String?>()
    val result : LiveData<String?>
        get() = _result

    init {
        retrofitInterface = AppModule.getApiService()
    }

    fun test() = viewModelScope.launch{
        _result.value = retrofitInterface.getRetrofit().toString()
        val jsonObject = JsonParser.parseString(result.value).asJsonObject
        val token = jsonObject.get("result")?.asString
        AccountHelper.setAuthToken(token)
    }
}