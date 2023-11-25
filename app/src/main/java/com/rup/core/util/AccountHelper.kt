package com.rup.core.util

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import java.util.concurrent.TimeUnit

object AccountHelper {
    private var accountManager: AccountManager? = null

    private const val MY_ACCOUNT_TYPE = "com.rup.auth.login"

    fun initAccountManager(context: Context) {
        accountManager = AccountManager.get(context)
    }

    fun getAccountManager(): AccountManager? = accountManager

    fun getMyAccounts(): Array<out Account>? = accountManager?.getAccountsByType(MY_ACCOUNT_TYPE)

    /**
     * 접근 가능한 모든 계정 목록을 불러옴
     * */
    fun getAccounts(): Array<out Account>? = accountManager?.accounts

    // 사용자 계정 추가 함수
    fun addAccount(id: String, pw: String) {
        Account(id, MY_ACCOUNT_TYPE).also { account ->
            accountManager?.addAccountExplicitly(account, pw, null)
        }
    }

    /**
     * 사용자 인증 토큰 설정
     * */
    fun setAuthToken(authToken: String?) {
        Log.d("token", authToken.toString())
        getMyAccounts()?.let { accounts ->
            val userAccount: Account = accounts[0]
            if (!authToken.isNullOrEmpty()) {
                accountManager?.setAuthToken(userAccount, userAccount.type, authToken)
            }
        } ?: {
            Log.e("setAuthToken", "getMyAccounts()")
        }
    }

    /**
     * 인증 토큰 요청하기
     * */
    fun getAuthToken(activity: Activity): String? {
        val accounts: Array<out Account>? = getMyAccounts()
        val account = accounts?.get(0)
        if (account != null) {
            val token = accountManager?.peekAuthToken(account, account.type)

            if (!token.isNullOrEmpty()) {
                return token
            } else {
                val feature = accountManager?.getAuthToken(
                    account,
                    account.type,
                    Bundle(),
                    activity,
                    OnTokenAcquired(),
                    null
                )

                return try {
                    // 5초 이상 데이터를 못불러오면 Exception 발생
                    feature?.getResult(5, TimeUnit.SECONDS)?.getString(AccountManager.KEY_AUTHTOKEN)
                } catch (e: Exception) {
                    ""
                }
            }
        } else {
            return ""
        }
    }

    /**
     * 계정 비밀번호 확인
     * */
    fun getPassword(account: Account?): String? {
        return try {
            accountManager?.getPassword(account)
        } catch (e: Exception) {
            ""
        }
    }

}
