package br.com.dogs.data.service.model.customSerializer

import br.com.dogs.data.service.model.HeightWeightDTO
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

@Serializable(with = ParentSerializer::class)
open class Parent
data class StringHolder(val text: String): Parent()

object StringHolderSerializer: KSerializer<StringHolder> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("string holder", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StringHolder) {
        encoder.encodeString(value.text)
    }

    override fun deserialize(decoder: Decoder): StringHolder {
        return StringHolder(decoder.decodeString())
    }
}

object ParentSerializer : JsonContentPolymorphicSerializer<Parent>(Parent::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Parent> {
        return when {
            element is JsonPrimitive && element.isString -> StringHolderSerializer
            element is JsonObject -> HeightWeightDTO.serializer()
            else -> throw IllegalArgumentException("Invalid JSON ${element}")
        }
    }
}