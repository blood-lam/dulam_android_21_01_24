package com.lamdx.smartmovie.ui.movie_detail

import androidx.lifecycle.ViewModel
import com.lamdx.smartmovie.domain.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(movieDetailUseCase: GetMovieDetailUseCase) :
    ViewModel() {

    val movieDetailFLow = { movieId: Int ->
        movieDetailUseCase.loadMovieDetails(movieId).flowOn(Dispatchers.IO)
    }

    val movieCredit = { movieId: Int ->
        movieDetailUseCase.loadMovieCast(movieId).flowOn(Dispatchers.IO)
    }
}