package com.rup.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RetrofitViewModel: ViewModel() {
    var retrofitInterface : RetrofitClient

    private val _result = MutableLiveData<String?>()
    val result : LiveData<String?>
        get() = _result

    init {
        retrofitInterface = AppModule.getApiService()
    }

    fun test() = viewModelScope.launch{
        Log.d("MainViewModel", retrofitInterface.toString())
        _result.value = retrofitInterface.getRetrofit().toString()
    }

}