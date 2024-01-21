package com.lamdx.smartmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompany(
    var id: Int,

    @SerializedName("logo_path")
    var logoPath: String,

    var name: String,

    @SerializedName("origin_country")
    var originCountry: String,
) : Parcelable
