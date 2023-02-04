package br.com.domain.repositories

import br.com.domain.entities.DogsBreed

interface DogBreedsRepository {
    fun getAllDogBreeds() : Result<ArrayList<DogsBreed>>
}