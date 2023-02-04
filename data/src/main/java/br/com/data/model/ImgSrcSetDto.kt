package br.com.data.model

import com.google.gson.annotations.SerializedName

data class ImgSrcSetDto(
    @SerializedName("1.5x")
    val length_img_1_5x: String,
    @SerializedName("2x")
    val length_img_2x: String
)