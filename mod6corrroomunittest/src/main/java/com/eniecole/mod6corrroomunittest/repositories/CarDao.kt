package com.eniecole.mod6corrroomunittest.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eniecole.mod6corrroomunittest.data.Car

@Dao
interface CarDao {
    @Insert
    suspend fun insert(car : Car) : Long

    @Insert
    suspend fun insertAll(cars : List<Car>) : List<Long>

    @Query("SELECT * FROM car")
    fun getAllCars() : List<Car>

}