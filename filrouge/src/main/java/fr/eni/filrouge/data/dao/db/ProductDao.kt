package fr.eni.filrouge.data.dao.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.eni.filrouge.data.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public suspend fun insert(product: Product) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public suspend fun insertAll(products: List<Product>) : List<Long>

    @Query("SELECT * FROM product")
    public suspend fun getAll() : List<Product>

    @Query("SELECT * FROM product WHERE id = :id")
    public suspend fun getById(id : Int) : Product

    @Query("DELETE FROM product")
    public suspend fun deleteAll()
}