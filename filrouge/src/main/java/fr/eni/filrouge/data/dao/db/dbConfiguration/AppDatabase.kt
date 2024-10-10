package fr.eni.filrouge.data.dao.db.dbConfiguration

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eni.filrouge.data.dao.db.ProductDao
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.model.StringListConverter

@Database(entities =[Product::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase(){
    //DAOs
    abstract fun productDao() : ProductDao

}