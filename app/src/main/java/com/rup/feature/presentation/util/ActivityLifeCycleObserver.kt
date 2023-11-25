package com.rup.feature.presentation.util

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class ActivityLifeCycleObserver : DefaultLifecycleObserver {

    companion object {
        const val TAG = "ActivityLife - 로그"
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "onCreated")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "onStart")
        // 생략...
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "onDestroy")
    }

}