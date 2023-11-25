package com.rup.feature.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import com.rup.databinding.DialogFragmentLoadingBinding


class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DialogFragmentLoadingBinding.inflate(layoutInflater).root)
        setCancelable(false)
        window?.setDimAmount(0.0f)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return false
        return super.onKeyDown(keyCode, event)
    }
}