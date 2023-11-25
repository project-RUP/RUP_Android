package com.rup

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kakao.sdk.auth.Constants
import com.kakao.sdk.common.KakaoSdk
import com.naver.maps.map.NaverMapSdk

class RUPApplication: Application() {
    companion object{
        lateinit var context: Context

        lateinit var sharedPreferences: SharedPreferences

        var accessToken:String? = Constants.ACCESS_TOKEN
    }
    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("dnoqf0zo8y")

        KakaoSdk.init(this, "6866af45323046a3b36f1292a9994240")

        sharedPreferences =
            applicationContext.getSharedPreferences("", MODE_PRIVATE)
    }
}