package com.lamdx.smartmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountry(

    @SerializedName("iso_3166_1")
    var iso3166P1: String,

    var name: String
) : Parcelable
