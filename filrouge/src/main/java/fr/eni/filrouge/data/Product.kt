package fr.eni.filrouge.data

data class Product (
    val id : Int,
    val name : String,
    val price : Int,
    val category : String,
    val url : String,
    val description : String,
    val caracteristics : List<String> = emptyList()
)