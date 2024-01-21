package com.lamdx.smartmovie.model

data class SearchMovie(

    var backdropPath: String?,

    var genres: List<Genres?>,

    var id: Int,

    var title: String,

    var voteAverage: Float,
)
