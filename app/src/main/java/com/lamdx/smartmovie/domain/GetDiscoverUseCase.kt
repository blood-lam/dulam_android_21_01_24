package com.lamdx.smartmovie.domain

import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.repository.impl.RestApiServiceImpl
import com.lamdx.smartmovie.utils.Constant.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDiscoverUseCase @Inject constructor(
    private val apiHelper: RestApiServiceImpl,
) {
    operator fun invoke(page: Int): Flow<List<List<Movie>>> = combine(
        loadMoviePopular(page),
        loadMovieTopRate(page),
        loadMovieUpComing(page),
        loadMovieNowPlaying(page)
    ) { popular, topRate, upComing, nowPlaying ->
        val fPopular = popular.take(4).toTypedArray()
        val fTopRate = topRate.take(4).toTypedArray()
        val fUpComing = upComing.take(4).toTypedArray()
        val fNowPlaying = nowPlaying.take(4).toTypedArray()
        val movie = arrayListOf(*fPopular, *fTopRate, *fUpComing, *fNowPlaying)
        arrayListOf(movie, popular, topRate, upComing, nowPlaying)
    }

    fun loadMoviePopular(page: Int): Flow<List<Movie>> {
        return apiHelper.getMoviePopular(API_KEY, page)
            .map { moviePagination ->
                moviePagination.results
            }
    }

    fun loadMovieTopRate(page: Int): Flow<List<Movie>> {
        return apiHelper.getMovieTopRate(API_KEY, page)
            .map { moviePagination ->
                moviePagination.results
            }
    }

    fun loadMovieUpComing(page: Int): Flow<List<Movie>> {
        return apiHelper.getMovieUpComing(API_KEY, page)
            .map { moviePagination ->
                moviePagination.results
            }
    }

    fun loadMovieNowPlaying(page: Int): Flow<List<Movie>> {
        return apiHelper.getMovieNowPlaying(API_KEY, page)
            .map { moviePagination ->
                moviePagination.results
            }
    }
}