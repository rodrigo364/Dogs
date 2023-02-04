package br.com.dogs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.dogs.data.service.model.DogsBreedsDTO
import br.com.dogs.databinding.ViewHolderBreedsBinding

class DogsBreedsAdapter : RecyclerView.Adapter<DogsBreedsAdapter.DogsBreedsViewHolder>() {

    private var dogsBreedsList = DogsBreedsDTO()

    fun addDogsBreedsList(breeds: DogsBreedsDTO){
        dogsBreedsList = breeds
        notifyDataSetChanged()
    }

    inner class DogsBreedsViewHolder(val viewHodlerBreeds: ViewHolderBreedsBinding ) :
        RecyclerView.ViewHolder(viewHodlerBreeds.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsBreedsViewHolder {
        val binding = ViewHolderBreedsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DogsBreedsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  dogsBreedsList.size
    }

    override fun onBindViewHolder(holder: DogsBreedsViewHolder, position: Int) {
        val binding = holder.viewHodlerBreeds
        val dogs = this.dogsBreedsList[position]
        binding.tvBreed.text = dogs.breed
        binding.tvOrigin.text = dogs.origin
    }
}