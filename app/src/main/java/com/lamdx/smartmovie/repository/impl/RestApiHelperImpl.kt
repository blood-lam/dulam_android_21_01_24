package com.lamdx.smartmovie.repository.impl

import com.lamdx.smartmovie.model.Genres
import com.lamdx.smartmovie.model.GenresList
import com.lamdx.smartmovie.repository.RestApiHelper
import com.lamdx.smartmovie.repository.RestApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestApiServiceImpl @Inject constructor(
    private val service: RestApiService,
) : RestApiHelper {

    override fun getAppConfiguration(apiKey: String) = flow {
        emit(service.getImageConfiguration(apiKey))
    }

    override fun getMoviePopular(apiKey: String, page: Int) = flow {
        emit(service.getMoviePopular(apiKey, page))
    }

    override fun getMovieTopRate(apiKey: String, page: Int) = flow {
        emit(service.getMovieTopRate(apiKey, page))
    }

    override fun getMovieUpComing(apiKey: String, page: Int) = flow {
        emit(service.getMovieUpComing(apiKey, page))
    }

    override fun getMovieNowPlaying(apiKey: String, page: Int) = flow {
        emit(service.getMovieNowPlaying(apiKey, page))
    }

    override fun getMovieDetails(id: Int, apiKey: String) = flow {
        emit(service.getMovieDetails(id, apiKey))
    }

    override fun getMovieCredits(id: Int, apiKey: String) = flow {
        emit(service.getMovieCredits(id, apiKey))
    }

    override fun searchMovie(apiKey: String, query: String, page: Int) = flow {
        emit(service.searchMovie(apiKey, query, page))
    }

    override fun getGenres(apiKey: String): Flow<GenresList> = flow {
        emit(service.getGenres(apiKey))
    }
}