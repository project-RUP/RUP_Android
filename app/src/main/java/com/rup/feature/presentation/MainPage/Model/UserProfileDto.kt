package com.rup.feature.presentation.MainPage.Model

data class UserProfileDto(
    val name: String,
    val profileImage: String, // 프로필 이미지의 URL 또는 리소스 ID
    val deposit: Int
)