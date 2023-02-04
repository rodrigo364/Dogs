package br.com.data.model

data class DogsBreedDto(
    val breed: String,
    val img: String,
    val meta: MetaDto,
    val origin: String,
    val url: String
)