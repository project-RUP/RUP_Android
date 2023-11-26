package com.rup.feature.data.remote.dto.user

data class SignUpReq (
    val kakaoId : String,
    val nickname : String,
    val type : String,
    val keyword : String
)
