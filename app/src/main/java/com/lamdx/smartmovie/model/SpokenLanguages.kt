package com.lamdx.smartmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguages(

    @SerializedName("english_name")
    var englishName: String,

    @SerializedName("iso_639_1")
    var iso639P1: String,

    var name: String
) : Parcelable
