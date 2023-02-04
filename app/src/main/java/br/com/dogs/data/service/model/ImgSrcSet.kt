package br.com.dogs.data.service.model

import com.google.gson.annotations.SerializedName

data class ImgSrcSet(
    @SerializedName("1.5x")
    val O_1_5x: String,
    @SerializedName("2x")
    val O_2_x: String
)