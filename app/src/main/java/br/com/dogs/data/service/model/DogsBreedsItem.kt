package br.com.dogs.data.service.model

import br.com.dogs.data.service.model.customSerializer.MetaParent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogsBreedsItem(
    @SerialName("breed")
    val breed: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("img")
    val img: String? = null,
    @SerialName("meta")
    val meta: MetaParent? = null,
    @SerialName("origin")
    val origin: String? = null,
    @SerialName("url")
    val url: String? = null
)

