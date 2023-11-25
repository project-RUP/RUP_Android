package com.rup.feature.presentation.map.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

data class MapMarker(
    val lat: Double,
    val lng: Double,
    val image: String
){
    private val marker = Marker()

    fun build(context: Context, naverMap: NaverMap){
        Glide
            .with(context)

            .asBitmap()
            .load(image)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    marker.icon = OverlayImage.fromBitmap(resource)
                    marker.position = LatLng(lat, lng)
                    marker.map = naverMap
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    Log.d("LOGEE", "onLoadCleared: ")
                }
            })

    }

    fun removeMapMarker(){
        marker.map = null
    }


}

fun MapMarker.toLatLng() = LatLng(this.lat, this.lng)
