package com.rup.feature.data.remote.dto.map

import com.google.gson.annotations.SerializedName

data class GetPromisesV2(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: ResultXX
)