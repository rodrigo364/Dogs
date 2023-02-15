package br.com.dogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.dogs.adapter.DogsBreedsAdapter
import br.com.dogs.contracts.MainActivityContract
import br.com.dogs.data.service.DogsService
import br.com.dogs.data.service.model.DogsBreedsDTO
import br.com.dogs.databinding.ActivityMainBinding
import br.com.dogs.model.MainModel
import br.com.dogs.presenter.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var dogsService: DogsService

    private lateinit var presenter: MainPresenter

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
    get() = _binding!!

    private val breedsAdapter = DogsBreedsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this, MainModel(dogsService))
        initView()
    }

    private fun initView() {
        binding.dogsBreedsRecyclerView.adapter = breedsAdapter
        presenter.getDogsBreeds()
    }

    override fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE

    }

    override fun onSuccess(breeds: DogsBreedsDTO) {
        binding.progressBar.visibility = View.GONE
        breedsAdapter.addDogsBreedsList(breeds)
    }

    override fun onError(message: String) {
        binding.progressBar.visibility = View.GONE
        Log.d("ERRO",message)
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        binding.tvError.text = message
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}