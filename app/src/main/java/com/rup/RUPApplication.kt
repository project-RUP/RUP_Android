package com.rup

import android.app.Application
import com.naver.maps.map.NaverMapSdk

class RUPApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("dnoqf0zo8y")
    }
}