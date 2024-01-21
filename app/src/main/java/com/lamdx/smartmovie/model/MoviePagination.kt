package com.lamdx.smartmovie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePagination(
    var page: Int,
    var results: List<Movie>
) : Parcelable {}
