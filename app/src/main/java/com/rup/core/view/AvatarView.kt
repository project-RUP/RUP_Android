package com.rup.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.rup.R
import com.rup.databinding.AvatarViewBinding

class AvatarView : FrameLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private val binding by lazy {
        AvatarViewBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.avatar_view, this, false)
        )
    }

    private fun initView() {
        addView(binding.root)
    }

    fun setImageUrl(url: String){
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .into(binding.avatar)
    }

    fun setImageResource(@DrawableRes id: Int){
        Glide
            .with(context)
            .load(id)
            .centerCrop()
            .into(binding.avatar)
    }
}