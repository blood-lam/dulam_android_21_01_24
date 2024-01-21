package com.lamdx.smartmovie.utils

import com.lamdx.smartmovie.repository.RestApiService
import com.lamdx.smartmovie.repository.impl.RestApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"


    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RestApiService = getRetrofit().create(RestApiService::class.java)

    @Singleton
    @Provides
    fun providerRestApiService(restApiService: RestApiService): RestApiServiceImpl = RestApiServiceImpl(restApiService)

}