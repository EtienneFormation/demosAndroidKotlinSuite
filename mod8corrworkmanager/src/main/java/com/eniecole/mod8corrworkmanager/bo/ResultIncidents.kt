package com.eniecole.mod8corrworkmanager.bo

import kotlinx.serialization.Serializable

@Serializable
data class ResultIncidents (
    val total_count : Int,
    //@Serializable(with = IncidentListSerializer::class)
    val results: List<Incident>

)

/*
object IncidentListSerializer : KSerializer<List<Incident>> {
    private val listSerializer = ListSerializer(Incident.serializer())

    override val descriptor: SerialDescriptor = listSerializer.descriptor

    override fun serialize(encoder: Encoder, value: List<Incident>) {
        listSerializer.serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): List<Incident> {
        return listSerializer.deserialize(decoder)
    }
}*/