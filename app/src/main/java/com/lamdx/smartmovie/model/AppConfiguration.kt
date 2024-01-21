package com.lamdx.smartmovie.model

import com.google.gson.annotations.SerializedName

data class AppConfiguration(
    var images: Image,

    @SerializedName("change_keys")
    var changeKeys: List<String>
) {}
