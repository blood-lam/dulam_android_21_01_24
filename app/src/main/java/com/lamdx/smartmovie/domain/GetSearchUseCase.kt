package com.lamdx.smartmovie.domain

import com.lamdx.smartmovie.model.GenresList
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.repository.impl.RestApiServiceImpl
import com.lamdx.smartmovie.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val apiHelper: RestApiServiceImpl,
) {

    fun loadGenres() = apiHelper.getGenres(Constant.API_KEY)
        .map {it: GenresList -> it.genres }
        .filter { genres ->
            return@filter genres.isNotEmpty()
        }

    fun searchMovie(query: String, page: Int = 1): Flow<List<Movie>> =
        apiHelper.searchMovie(Constant.API_KEY, query, page)
            .map { it.results.filter { movie -> return@filter movie.backdropPath !== null } }

}