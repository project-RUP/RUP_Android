package com.rup.network

import com.rup.di.AppModule
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 요청 및 응답 바디를 포함한 모든 정보를 로그로 출력
        }

        return Retrofit.Builder()
            .baseUrl(CoreData.BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(AppModule.gson))
            .build()
    }

}