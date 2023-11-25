package com.rup.network

import com.google.gson.JsonElement
import retrofit2.http.GET

interface RetrofitClient {
    @GET("health")
    suspend fun getRetrofit(): JsonElement

}