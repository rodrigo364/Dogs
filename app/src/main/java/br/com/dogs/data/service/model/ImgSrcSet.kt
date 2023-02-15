package br.com.dogs.data.service.model

import br.com.dogs.data.service.model.customSerializer.ParentImage
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImgSrcSet(
    @SerialName("1.5x")
    val O_1_5x: String? = null,
    @SerialName("2x")
    val O_2_x: String? = null
) : ParentImage()