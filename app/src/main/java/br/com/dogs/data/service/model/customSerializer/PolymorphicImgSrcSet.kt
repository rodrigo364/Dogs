package br.com.dogs.data.service.model.customSerializer

import br.com.dogs.data.service.model.ImgSrcSet
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive


@Serializable(with = ParentImageSerializer::class)
open class ParentImage
data class StringImageHolder(val text: String): ParentImage()

object StringImageHolderSerializer: KSerializer<StringImageHolder> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("string holder", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StringImageHolder) {
        encoder.encodeString(value.text)
    }

    override fun deserialize(decoder: Decoder): StringImageHolder {
        return StringImageHolder(decoder.decodeString())
    }
}

object ParentImageSerializer : JsonContentPolymorphicSerializer<ParentImage>(ParentImage::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ParentImage> {
        return when {
            element is JsonPrimitive && element.isString -> StringImageHolderSerializer
            element is JsonObject -> ImgSrcSet.serializer()
            else -> throw IllegalArgumentException("Invalid JSON ${element}")
        }
    }
}