package br.com.dogs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dogs.R
import br.com.dogs.data.service.model.DogsBreedsDTO
import br.com.dogs.data.service.model.HeightWeightDTO
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
        binding.lbOrigin.text = dogs.origin
        var img02x : String?  = null
        var otherName : String? = ""
        try {
             val castMeta = dogs.meta as Meta
             val castImgSrcSet  = castMeta.imgSrcSet as ImgSrcSet
             img02x = castImgSrcSet.O_2_x
             otherName = castMeta.otherNames
            binding.lbCoat.text = castMeta.coat

            //Height
            if (dogs.meta.height is HeightWeightDTO) {
                binding.tvheight.text =  (dogs.meta.height as HeightWeightDTO).dogs

            } else {
               binding.tvheight.text =  dogs.meta.height as String
            }

            //Weight
            if (dogs.meta.weight is HeightWeightDTO) {
                binding.tvheight.text =  (dogs.meta.weight as HeightWeightDTO).dogs

            } else {
                binding.tvWeight.text =  dogs.meta.height as String
            }


        } catch (e: Exception) {
            println("error")
        }

        binding.tvOtherNames.text = otherName


        Picasso.get()
            .load(img02x)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .fit()
            .centerInside()
            .placeholder(R.mipmap.ic_dog_placeholder)
            .error(R.mipmap.ic_error_dog)
            .into(binding.imgDogs, object : Callback {
                override fun onSuccess() {
                    binding.progressLoadingImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressLoadingImage.visibility = View.GONE
                }

            })
    }

}