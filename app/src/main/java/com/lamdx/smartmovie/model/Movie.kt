package com.lamdx.smartmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var adult: Boolean,

    @SerializedName("backdrop_path") var backdropPath: String?,

    @SerializedName("genre_ids") var genreIds: List<Int>,

    var id: Int,

    @SerializedName("original_language") var originalLanguage: String,

    @SerializedName("original_title") var originalTitle: String,

    var overview: String,

    var popularity: Float,

    @SerializedName("poster_path") var posterPath: String,

    @SerializedName("release_date") var releaseDate: String,

    var title: String,

    var video: Boolean,

    @SerializedName("vote_average") var voteAverage: Float,

    @SerializedName("vote_count") var voteCount: Int,
) : Parcelable
