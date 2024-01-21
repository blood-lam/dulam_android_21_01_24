package com.lamdx.smartmovie.model

import com.google.gson.annotations.SerializedName

data class Cast(
    var adult: Boolean,

    var gender: Int,

    var id: Int,

    @SerializedName("known_for_department")
    var knownForDepartment: String,

    var name: String,

    @SerializedName("original_name")
    var originalName: String,

    var popularity: Float,

    @SerializedName("profile_path")
    var profilePath: String?,

    @SerializedName("cast_id")
    var castId: Int,

    var character: String,

    @SerializedName("credit_id")
    var creditId: String,

    var order: Int
)
