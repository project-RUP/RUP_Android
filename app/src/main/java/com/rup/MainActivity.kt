package com.rup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rup.network.RetrofitViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(RetrofitViewModel::class.java)
        viewModel.test()

        viewModel.result.observe(this, Observer {
            Log.d("MainActivity",it.toString())
        })
    }
}