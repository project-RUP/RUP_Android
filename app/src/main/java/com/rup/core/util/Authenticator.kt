package com.rup.core.util

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.rup.MainActivity

class Authenticator(
    private val context: Context
) : AbstractAccountAuthenticator(context) {
    /**
     * '설정 > 계정 및 백업 > 계정 관리 > 계정 추가'에서 자신의 앱을 선택하면 KEY_INTENT로 넘겨주는 Intent를 실행해줌
     * 보통은 인증 정보를 받기위해 로그인 화면으로 이동시킨다. (ex: 네이버, 페이코)
     * */
    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        accountTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        Log.d("Authenticator", "addAccount()")

        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        }

        return Bundle().also {
            it.putParcelable(AccountManager.KEY_INTENT, intent)
        }
    }

    /**
     * 서비스 인증 토큰 반환
     * 인증 토큰을 서버에서 받아와 반환해주는 함수, 한 번만 서버에서 토큰을 받아오면 Account에 저장됨
     * 인증 토큰을 받지 못하면 사용자 정보를 다시 받을 수 있게 로그인 화면으로 이동시킴(인증 실패 3번 사례)
     * */
    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {

        return Bundle()
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle? {
        return null
    }


    override fun getAuthTokenLabel(p0: String?): String? {
        return null
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle? {
        return null
    }

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle? {
        return null
    }
}
