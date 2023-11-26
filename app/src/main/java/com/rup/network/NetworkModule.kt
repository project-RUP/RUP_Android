package com.rup.network

import android.util.Log
import com.rup.RUPApplication
import com.rup.di.AppModule
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 요청 및 응답 바디를 포함한 모든 정보를 로그로 출력
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            // 다른 인터셉터 추가 가능
            .build()

        return Retrofit.Builder()
            .baseUrl(CoreData.BASE_SERVER_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(AppModule.gson))
            .build()
    }

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiQVVUSE9SSVRJRVMiOlt7ImF1dGhvcml0eSI6IlVTRVIifV0sImlhdCI6MTcwMDk1NjQzNSwiZXhwIjoxNzAxMzE2NDM1fQ.ecCeVLB-k-uM3GR4VtBxbcXNqTPPgpscRu9pfgI1D3U")
            .build()
        chain.proceed(modifiedRequest)
    }

}