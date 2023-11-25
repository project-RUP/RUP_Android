package com.rup.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rup.core.util.AccountHelper
import com.rup.feature.data.remote.UserAuthApi
import com.rup.feature.presentation.login.LoginActivity
import com.rup.network.CoreData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AuthModule {

    private val userAuthApi: UserAuthApi by lazy {
        getUserRetrofit().create(UserAuthApi::class.java)
    }

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getUserRetrofit(): Retrofit {
        var retrofitClient : Retrofit? = null

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 요청 및 응답 바디를 포함한 모든 정보를 로그로 출력
        }
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofitClient = Retrofit.Builder()
            .baseUrl(CoreData.BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            //.client(client.build())
            .build()

        return retrofitClient
    }

    fun getApiService(): UserAuthApi {
        return AuthModule.getUserRetrofit().create()
    }
}