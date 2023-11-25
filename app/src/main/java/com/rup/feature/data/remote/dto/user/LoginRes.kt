package com.rup.feature.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class LoginRes (
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: TokenData
)

data class TokenData(
    @SerializedName("accessToken")
    val accessToken: String
)
