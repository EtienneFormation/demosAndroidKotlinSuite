package fr.eni.filrouge.dbConfiguration

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eni.filrouge.dao.ProductDao
import fr.eni.filrouge.data.Product
import fr.eni.filrouge.data.StringListConverter

@Database(entities =[Product::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase(){
    //DAOs
    abstract fun productDao() : ProductDao

}