package com.rup.feature.data.remote

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.POST

interface UserAuthApi {
    @POST("/")
    suspend fun signUp(): Response<JsonElement>
}