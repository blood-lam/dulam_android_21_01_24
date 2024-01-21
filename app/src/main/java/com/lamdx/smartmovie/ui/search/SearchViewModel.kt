package com.lamdx.smartmovie.ui.search

import androidx.lifecycle.ViewModel
import com.lamdx.smartmovie.domain.GetSearchUseCase
import com.lamdx.smartmovie.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: GetSearchUseCase) :
    ViewModel() {

    var searchedMovie: Flow<List<Movie>> = emptyFlow()

    val searchFlow = { query: String, page: Int -> searchUseCase.searchMovie(query, page) }

}