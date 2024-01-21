package com.lamdx.smartmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lamdx.smartmovie.domain.GetConfigurationUseCase
import com.lamdx.smartmovie.domain.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    appConfigurationUseCase: GetConfigurationUseCase,
    searchUseCase: GetSearchUseCase,
) :
    ViewModel() {

    val targetViewModel: MutableLiveData<String> by lazy {
        MutableLiveData<String>("Detail Mode")
    }

    val configuration = appConfigurationUseCase.invoke().asLiveData()

    val genresFlow = searchUseCase.loadGenres()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )
}