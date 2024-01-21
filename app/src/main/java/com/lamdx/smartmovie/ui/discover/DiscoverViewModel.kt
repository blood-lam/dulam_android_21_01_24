package com.lamdx.smartmovie.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamdx.smartmovie.domain.GetDiscoverUseCase
import com.lamdx.smartmovie.model.Movie
import com.lamdx.smartmovie.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    discoverUseCase: GetDiscoverUseCase,
) : ViewModel() {

    val loadPopular =
        discoverUseCase.loadMoviePopular(1).flowOn(Dispatchers.IO).map { ApiResult.Success(it) }
            .catch { ApiResult.Error(it.message) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ApiResult.Loading(emptyList())
            )

    val loadTopRate =
        discoverUseCase.loadMovieTopRate(1).flowOn(Dispatchers.IO).map { ApiResult.Success(it) }
            .catch { ApiResult.Error(it.message) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ApiResult.Loading(emptyList())
            )

    val loadUpComing =
        discoverUseCase.loadMovieUpComing(1).flowOn(Dispatchers.IO).map { ApiResult.Success(it) }
            .catch { ApiResult.Error(it.message) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ApiResult.Loading(emptyList())
            )

    val loadNowPlaying =
        discoverUseCase.loadMovieNowPlaying(1).flowOn(Dispatchers.IO).map { ApiResult.Success(it) }
            .catch { ApiResult.Error(it.message) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ApiResult.Loading(emptyList())
            )

    val discoverState =
        discoverUseCase.invoke(1).flowOn(Dispatchers.IO).map { ApiResult.Success(it) }
            .catch { ApiResult.Error(it.message) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ApiResult.Loading(emptyList())
            )

    val tabMovieData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val tabPopularData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val tabTopRateData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val tabUpComingData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    val tabNowPlayingData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

}