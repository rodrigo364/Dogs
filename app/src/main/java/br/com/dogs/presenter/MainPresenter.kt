package br.com.dogs.presenter

import br.com.dogs.contracts.MainActivityContract
import br.com.dogs.data.service.model.DogsBreedsDTO
import br.com.dogs.lauchOnMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainActivityContract.View,
    private val model: MainActivityContract.Model
) : MainActivityContract.Presenter, MainActivityContract.Model.onFinishListener  {

    val scope = CoroutineScope(Dispatchers.IO)
    override fun getDogsBreeds() {
        scope.launch {
            model.fetchDogsBreeds(onFinishListener = this@MainPresenter)
        }

    }

    override fun onDestroy() {
        scope.cancel()
    }


    //Implements Model Interface
    override fun onLoading() {
        scope.lauchOnMain { view.onLoading() }

    }

    override fun onError(message: String) {
        scope.lauchOnMain {  view.onError(message) }
    }

    override fun onSuccess(dogsBreedsDTO: DogsBreedsDTO) {
        scope.lauchOnMain {  view.onSuccess(dogsBreedsDTO) }
    }
}