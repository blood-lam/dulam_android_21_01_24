package com.lamdx.smartmovie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    var id: Int,
    var name: String
) : Parcelable
