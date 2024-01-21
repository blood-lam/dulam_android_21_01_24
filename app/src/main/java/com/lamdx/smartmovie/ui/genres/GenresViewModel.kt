package com.lamdx.smartmovie.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GenresViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is genres Fragment"
    }
    val text: LiveData<String> = _text
}