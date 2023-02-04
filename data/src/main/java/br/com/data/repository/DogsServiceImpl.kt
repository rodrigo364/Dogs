package br.com.data.repository

import br.com.data.model.DogsBreedDto
import br.com.data.network.service.DogsService
import retrofit2.Response

class DogsRepositoryImpl (
    private val  service : DogsService
        ) : DogsRepository {
    override suspend fun getAllDogBreeds(): List<DogsBreedDto> {

        return arrayListOf()
    }
}