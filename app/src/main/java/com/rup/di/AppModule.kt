package com.rup.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rup.RUPApplication
import com.rup.core.util.AccountHelper
import com.rup.network.CoreData
import com.rup.network.RetrofitClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val token : String? = RUPApplication.accessToken

        Log.d("token",token.toString())

        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        chain.proceed(modifiedRequest)
    }

    private val retrofitClient: RetrofitClient by lazy {
        getRetrofit().create(RetrofitClient::class.java)
    }

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 요청 및 응답 바디를 포함한 모든 정보를 로그로 출력
            // 추가적인 설정
            // 예를 들어, 로깅 형식을 변경하거나 특정 URL에 대해서만 로그를 출력하는 등의 설정을 추가할 수 있습니다.
            // 예: addInterceptor(MyInterceptor())
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            // 다른 인터셉터 추가 가능
            .build()

        return Retrofit.Builder()
            .baseUrl(CoreData.BASE_SERVER_URL)
            .client(client) // OkHttpClient 추가
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getApiService(): RetrofitClient {
        return retrofitClient
    }

}