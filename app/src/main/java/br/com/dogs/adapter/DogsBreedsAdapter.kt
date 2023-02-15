package br.com.dogs.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dogs.R
import br.com.dogs.data.service.model.DogsBreedsDTO
import br.com.dogs.data.service.model.ImgSrcSet
import br.com.dogs.data.service.model.Meta
import br.com.dogs.databinding.ViewHolderBreedsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class DogsBreedsAdapter : RecyclerView.Adapter<DogsBreedsAdapter.DogsBreedsViewHolder>() {

    private var dogsBreedsList = DogsBreedsDTO()
    private lateinit var context: Context
    fun addDogsBreedsList(breeds: DogsBreedsDTO){
        dogsBreedsList = breeds
        notifyDataSetChanged()
    }

    inner class DogsBreedsViewHolder(val viewHodlerBreeds: ViewHolderBreedsBinding ) :
        RecyclerView.ViewHolder(viewHodlerBreeds.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsBreedsViewHolder {
        val binding = ViewHolderBreedsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
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
        var img02x : String?  = null
        try{
            val castMeta = dogs.meta as Meta
             val castImgSrcSet  = castMeta.imgSrcSet as ImgSrcSet
            img02x = castImgSrcSet.O_2_x

        }catch (e: Exception) {
            println("error")
        }



        Picasso.get()
            .load(img02x)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .placeholder(R.mipmap.ic_dog_placeholder)
            .error(R.mipmap.ic_error_dog)
            .into(binding.imgDogs)



    }
}