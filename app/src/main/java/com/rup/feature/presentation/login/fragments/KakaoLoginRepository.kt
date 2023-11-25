package com.rup.feature.presentation.login.fragments

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoLoginRepository {
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KakaoLoginRepository", "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i("KakaoLoginRepository1", "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

    fun loginWithKakao(context: Context): Boolean {
        var loginSuccess = false // 기본적으로 로그인 실패로 초기화

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("KakaoLoginRepository", "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        // 사용자가 로그인을 취소한 경우
                        // return@loginWithKakaoTalk 를 사용해도 됩니다.
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context) { _, _ ->
                        // 성공 여부에 따라 로그인 성공을 반환
                        loginSuccess = (error == null)
                    }
                } else if (token != null) {
                    Log.i("KakaoLoginRepository", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    loginSuccess = true // 로그인 성공으로 설정
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { _, _ ->
                // 성공 여부에 따라 로그인 성공을 반환
                loginSuccess = true // 로그인 성공으로 설정
            }
            loginSuccess = true // 로그인 성공으로 설정
        }

        return loginSuccess
    }
}