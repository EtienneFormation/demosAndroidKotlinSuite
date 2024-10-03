package fr.eni.mod6demosqllite.data

data class Book (
    val id : Long,
    val name : String,
    val isbn : String,
    val releaseDate : String,
    val editor : String,
    val author : String
)
