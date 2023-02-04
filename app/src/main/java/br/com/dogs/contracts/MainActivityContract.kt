package br.com.dogs.contracts

import br.com.dogs.data.service.model.DogsBreedsDTO

interface MainActivityContract {

    interface View{
        fun onLoading()
        fun onSuccess(breeds: DogsBreedsDTO)
        fun onError(message: String)
    }

    interface Presenter{
        fun getDogsBreeds()
        fun onDestroy()
    }

    interface Model{
        interface onFinishListener{
            fun onLoading()
            fun onError(message: String)
            fun onSuccess(dogsBreedsDTO: DogsBreedsDTO)
        }

        suspend fun fetchDogsBreeds(onFinishListener: onFinishListener)
    }
}