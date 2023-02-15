package br.com.dogs.data.service.model

import br.com.dogs.data.service.model.customSerializer.Parent
import kotlinx.serialization.Serializable

@Serializable
data class HeightWeightDTO(
    val bitches: String = "",
    val dogs: String = ""
) : Parent()