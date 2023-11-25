package com.rup.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rup.feature.data.remote.UserAuthApi
import com.rup.network.CoreData
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthModule {

    private val userAuthApi: UserAuthApi by lazy {
        getUserRetrofit().create(UserAuthApi::class.java)
    }

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getUserRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 요청 및 응답 바디를 포함한 모든 정보를 로그로 출력
        }

        return Retrofit.Builder()
            .baseUrl(CoreData.BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getApiService(): UserAuthApi {
        return userAuthApi
    }
}