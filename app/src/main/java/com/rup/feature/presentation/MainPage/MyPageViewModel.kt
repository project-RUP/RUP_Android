package com.rup.feature.presentation.MainPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rup.core.base.BaseViewModel
import com.rup.feature.presentation.Main.model.maindto
import com.rup.feature.presentation.MainPage.Model.UserProfileDto

class MyPageViewModel: BaseViewModel() {
    private val _userProfile = MutableLiveData<UserProfileDto>()
    init {
        setUserProfile()
    }
    val userProfile: LiveData<UserProfileDto> get() = _userProfile

    private fun setUserProfile() {
        _userProfile.value = UserProfileDto("User","/////",9999)
    }
}
