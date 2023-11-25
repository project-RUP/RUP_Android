package com.rup.feature.data.remote

import com.google.gson.JsonElement
import com.rup.feature.data.remote.dto.user.MemberExistReq
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthApi {
    @POST("member/exists")
    suspend fun existCheck(@Body kakaoId : MemberExistReq): JsonElement

    @POST("member/login")
    suspend fun userLogin(@Body kakaoId: MemberExistReq) : JsonElement
}