package br.com.data.network.service

import br.com.data.model.DogsBreedDto
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {
    @GET("/dog_breeds")
    suspend fun getAllDogBreeds() : Response<ArrayList<DogsBreedDto>>
}