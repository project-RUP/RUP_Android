package com.rup.core.util

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.os.Bundle

class OnTokenAcquired : AccountManagerCallback<Bundle> {
    override fun run(result: AccountManagerFuture<Bundle>?) {

        val bundle = result?.result

        // 호출이 성공했다면 토큰을 가져옴
        val token = bundle?.getString(AccountManager.KEY_AUTHTOKEN)
    }
}