package com.eniecole.mod8corrworkmanager.bo

import kotlinx.serialization.Serializable

@Serializable
data class Incident (
    val intitule:String,
    val resume:String,
    val date_debut:String,
    val date_fin:String,
    val heure_debut:String,
    val heure_fin:String,
)


