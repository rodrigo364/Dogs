package br.com.dogs.data.service.model.customSerializer

import br.com.dogs.data.service.model.Meta
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

@Serializable(with = PolymorphicMetaSerializer::class)
open class MetaParent
data class StringMetaHolder(val text: String): MetaParent()
object StringMetaHolderSerializer: KSerializer<StringMetaHolder> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("string holder", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StringMetaHolder) {
        encoder.encodeString(value.text)
    }

    override fun deserialize(decoder: Decoder): StringMetaHolder {
        return StringMetaHolder(decoder.decodeString())
    }
}
object PolymorphicMetaSerializer : JsonContentPolymorphicSerializer<MetaParent>(MetaParent::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out MetaParent> {
        return when {
            element is JsonPrimitive && element.isString -> StringMetaHolderSerializer
            element is JsonObject -> Meta.serializer()
            else -> throw IllegalArgumentException("Invalid JSON ${element}")
        }
    }
}
