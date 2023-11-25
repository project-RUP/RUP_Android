package com.rup.map.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

data class MapMarker(
    val lat: Double,
    val lng: Double,
    val image: String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJAAAACQCAMAAADQmBKKAAAAbFBMVEX///+HxCSLxiv+//2azUb1+u2OxzCYzUTh8Mjn89On1F7v9+Gg0FK33Hydz0yPyDP4/PLy+ejJ5Jyq1WTP56et12q83oXo9Nbf78Xd7sD6/Pbr9duk0ljS6a33+/C+34jF45Wy2XPY7LjQ6KpCsl+MAAAD5klEQVR4nO2ci3LaQAxFWRtsCAGHZwJNGjv9/3+seTvGCyvp3qUzzfmAzBlHlrRamV7PxGwznyymeZE5lxX5+2q9HP62/UUD4/mqcB3k1XIcXSYdrvMumRP99Simzri8aXN0KmeRdEar+zZ7kuo1hs4iUGfPgq00Dn06ZypmgKfzROpT/+PmKctn1Jfr7Jhy3ri01OnsHxLBZ/Ck9qlZvKB9XgMyzy364Nh+y2w+zhXQQPpSvF1tsiHQx65Tk8CMNoDnsyMDpe0RyKeOI0i5nXU2PTqmW7tP+ozzcW5lF1ojfZxbWn02WB+X/LL5vAAD6MCzrfZP0D7OmQrtCO/jsoFBCPqGnaj0PuiIPqJP2JQHZEhGbxwf57S9kalHvMVE58N4xQ4kuiJLyEEnPjU+W3PX6qfQVH1Mm+hBU2MrptBU7pMS/2M18qI/pPoowvqTK5SLhd65Qk56bkxhRw0P0v8ZL00fkf7PPthC0goLPmx0IGxlRaNNFcKuyDgOCkAYROyXrEYmxPf5EfoR+t+F+K99IhOCj2GuKGRC/1ymZvdnzr3LhIinxCPC8/ScLiRsP2iTjzMbmdCWLiTdgCANq86Iz676C80wxCdF9rFDflfFzdWFfH7OPUuvxT69V6qQ5spjSvQR1o0DS6LQh0ZoywtrRUjv4IW18kZoxmobM+3iBasHKZU+vTHHRzWlZj4iw0XwmBFFueXWlfGIVDnoxAA/Pp/arqXxbZFxR2aLPqCZly3Al0LKu7sm2CNjafbB9rJ9wL4O9NWHbH0NcG2IYamhCaxTKyxrHw1gS1amHN0EFNdPKB/QVUwC3D6FLFqVOB9IvjYW1Tb2xRTgKuwOc6um3IjxY+xDCvgXOlvlQv4RWAq6YFp1AKagC5XeB5mCLsz0/XXJ8DHMrnNEF9RBqp0YfXF81HFNiegDlUqI+AWV6twIz9FNFHGNahO7UcS18JJFijiuS66POK4BnyncQZav8UX+GlFc01Jig1TQhxBTYoPwuLZ+xBFK8Kelqi1lBaFxXcA/TPQRGNd/YvkE5mvjRzciguIafBC7TcAQi5+jm9wfYgGmmyLuDrHKuD53h1isvt7PnUvrGEWsxc0hlurW2cjNIVaMH2u44sYOOLWv9+MdYnH7ej/eIRbjBwiC8Ayx+hGL2Hc8l2lvj/LxrM7FLWItqmsfznAqlI7mMVbf6uGqyMbrW7u5KrKEcauM1k3R86N92kU26g8zdfOtyILuMG00boqyyH2rh8uHTQ8rYt85F9n4fauHU5F9QN/azfGmKM7wJYjDSTbS8CWIXZFV7NvyqE+yjy5iLZb2H2HBkk5AfetfGRE7iZis9T4AAAAASUVORK5CYII="
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

                }
            })

    }

    fun removeMapMarker(){
        marker.map = null
    }
}
