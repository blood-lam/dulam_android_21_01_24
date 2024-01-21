package com.lamdx.smartmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    var adult: Boolean,

    @SerializedName("backdropPath") var backdropPath: String,

    @SerializedName("belongsToCollection") var belongsToCollection: String? = null,

    var budget: Int,

    var genres: List<Genres>,

    var homepage: String,

    var id: Int,

    @SerializedName("imdb_id") var imdbId: String,

    @SerializedName("original_language") var originalLanguage: String,

    @SerializedName("original_title") var originalTitle: String,

    var overview: String,

    var popularity: Float,

    @SerializedName("poster_path") var posterPath: String,

    @SerializedName("production_companies") var productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries") var productionCountries: List<ProductionCountry>,

    @SerializedName("release_date") var releaseDate: String,

    var revenue: Int,

    var runtime: Int,

    @SerializedName("spoken_languages") var spokenLanguages: List<SpokenLanguages>,

    var status: String,

    var tagline: String,

    var title: String,

    var video: Boolean,

    @SerializedName("vote_average") var voteAverage: Float,

    @SerializedName("vote_count") var voteCount: Int,
) : Parcelable