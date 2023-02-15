package br.com.dogs.model

import br.com.dogs.contracts.MainActivityContract
import br.com.dogs.data.service.DogsService
import br.com.dogs.data.service.model.DogsBreedsDTO

class MainModel(private val dogsService: DogsService) : MainActivityContract.Model {

    override suspend fun fetchDogsBreeds(onFinishListener: MainActivityContract.Model.onFinishListener) {
        onFinishListener.onLoading()
        try {
            val response = dogsService.getDogsBreeds()

            if(response.isSuccessful){
                response.body()?.let {
                    val d = DogsBreedsDTO()
                    d.addAll(it)
                    onFinishListener.onSuccess(d)
                }
            } else {
                onFinishListener.onError(response.errorBody().toString())
            }

        }catch (e: Exception) {
            onFinishListener.onError(message = e.message.toString())
        }
    }

}