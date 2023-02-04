package br.com.data.repository

import br.com.data.model.DogsBreedDto

interface DogsRepository {
    suspend fun getAllDogBreeds() : List<DogsBreedDto>
}