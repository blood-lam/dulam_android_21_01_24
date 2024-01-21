package com.lamdx.smartmovie.repository

import com.lamdx.smartmovie.model.AppConfiguration
import com.lamdx.smartmovie.model.Credit
import com.lamdx.smartmovie.model.GenresList
import com.lamdx.smartmovie.model.MovieDetails
import com.lamdx.smartmovie.model.MoviePagination
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RestApiService {
    @GET("configuration")
    suspend fun getImageConfiguration(@Query("api_key") apiKey: String): AppConfiguration

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MoviePagination

    @GET("movie/top_rated")
    suspend fun getMovieTopRate(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MoviePagination

    @GET("movie/upcoming")
    suspend fun getMovieUpComing(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MoviePagination

    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MoviePagination

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): MovieDetails

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id: Int, @Query("api_key") apiKey: String): Credit

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MoviePagination

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenresList

}