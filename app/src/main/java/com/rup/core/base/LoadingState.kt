package com.rup.core.base

sealed class LoadingState{
    object Dismiss : LoadingState()
    object Show : LoadingState()
    object ErrorDismiss : LoadingState()

}
