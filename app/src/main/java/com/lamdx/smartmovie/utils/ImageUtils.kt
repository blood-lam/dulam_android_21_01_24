package com.lamdx.smartmovie.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lamdx.smartmovie.R

val imagePresent = {view: View, imgUrl: String, imgView: ImageView ->
    Glide.with(view)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                .error(R.drawable.ic_launcher_background) // Error image in case of loading failure
        )
        .into(imgView)
}