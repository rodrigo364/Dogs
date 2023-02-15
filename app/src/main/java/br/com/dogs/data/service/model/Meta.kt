package br.com.dogs.data.service.model

import br.com.dogs.data.service.model.customSerializer.MetaParent
import br.com.dogs.data.service.model.customSerializer.Parent
import br.com.dogs.data.service.model.customSerializer.ParentImage
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.json.*







@Serializable
data class Meta(

    @SerialName("height")
    var height: Parent? = null,

    @SerialName("weight")
    var weight: Parent? = null,

    @SerialName("coat") var coat: String? = null,
    @SerialName("img_src_set") var imgSrcSet: ParentImage? = null,
    @SerialName("life_span") var lifeSpan: String?    = null,
    @SerialName("other_names") var otherNames: String?    = null,
    @SerialName("common_nicknames") var commonNicknames : String?    = null,
    @SerialName("colour") var colour: String?    = null,
    @SerialName("litter_size") var litterSize : String?    = null,
    @SerialName("notes") var notes: String?    = null,
    @SerialName("breed_status") var breedStatus: String?    = null,
    @SerialName("foundation_stock" ) var foundationStock : String?    = null
) : MetaParent()




