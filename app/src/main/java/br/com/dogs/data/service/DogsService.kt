package br.com.dogs.data.service

import br.com.dogs.data.service.model.DogsBreedsDTO
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {
    @GET("dog_breeds")
    suspend fun getDogsBreeds() : Response<DogsBreedsDTO>
}