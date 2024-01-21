package com.lamdx.smartmovie.domain

import com.lamdx.smartmovie.model.Credit
import com.lamdx.smartmovie.model.MovieDetails
import com.lamdx.smartmovie.repository.impl.RestApiServiceImpl
import com.lamdx.smartmovie.utils.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val apiServiceImpl: RestApiServiceImpl) {

    fun loadMovieDetails(movieId: Int): Flow<MovieDetails> {
        return apiServiceImpl.getMovieDetails(movieId, Constant.API_KEY)
    }

    fun loadMovieCast(movieId: Int): Flow<Credit> {
        return apiServiceImpl.getMovieCredits(movieId, Constant.API_KEY)
    }
}