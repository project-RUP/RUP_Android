package com.rup.feature.data.remote.dto.map

import com.google.gson.annotations.SerializedName

data class ResultXX(
    @SerializedName("address")
    val address: String,
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("inviteCode")
    val inviteCode: String,
    @SerializedName("leftDate")
    val leftDate: Int,
    @SerializedName("leftHour")
    val leftHour: Int,
    @SerializedName("leftMinute")
    val leftMinute: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("penalty")
    val penalty: Int,
    @SerializedName("promiseDate")
    val promiseDate: String,
    @SerializedName("promiseHour")
    val promiseHour: String,
    @SerializedName("promiseTime")
    val promiseTime: String
)