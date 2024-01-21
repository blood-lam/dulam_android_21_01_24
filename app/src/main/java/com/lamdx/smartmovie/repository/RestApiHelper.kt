package com.lamdx.smartmovie.repository

import com.lamdx.smartmovie.model.AppConfiguration
import com.lamdx.smartmovie.model.Credit
import com.lamdx.smartmovie.model.GenresList
import com.lamdx.smartmovie.model.MovieDetails
import com.lamdx.smartmovie.model.MoviePagination
import kotlinx.coroutines.flow.Flow

interface RestApiHelper {

    fun getAppConfiguration(apiKey: String): Flow<AppConfiguration>

    fun getMoviePopular(apiKey: String, page: Int): Flow<MoviePagination>

    fun getMovieTopRate(apiKey: String, page: Int): Flow<MoviePagination>

    fun getMovieUpComing(apiKey: String, page: Int): Flow<MoviePagination>

    fun getMovieNowPlaying(apiKey: String, page: Int): Flow<MoviePagination>

    fun getMovieDetails(id: Int, apiKey: String): Flow<MovieDetails>

    fun getMovieCredits(id: Int, apiKey: String): Flow<Credit>

    fun searchMovie(apiKey: String, query: String, page: Int): Flow<MoviePagination>

    fun getGenres(apiKey: String): Flow<GenresList>

}