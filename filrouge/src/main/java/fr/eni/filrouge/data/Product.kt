package fr.eni.filrouge.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val price : Int,
    val category : String,
    val url : String,
    val description : String,
    val characteristics : List<String> = emptyList()
)

class StringListConverter{
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun stringToList(strings: List<String>): String {
        return Gson().toJson(strings)
    }
}