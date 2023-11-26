package com.rup.feature.data.remote.dto.map

import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("penalty")
    val penalty: Int,
    @SerializedName("promiseTime")
    val promiseTime: String
)