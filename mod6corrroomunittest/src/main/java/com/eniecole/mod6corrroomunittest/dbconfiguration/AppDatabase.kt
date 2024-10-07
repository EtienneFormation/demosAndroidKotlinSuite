package com.eniecole.mod6corrroomunittest.dbconfiguration

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eniecole.mod6corrroomunittest.data.Car
import com.eniecole.mod6corrroomunittest.repositories.CarDao

@Database(entities = [Car::class], version = 1)
abstract  class AppDatabase : RoomDatabase() {
    abstract fun carDao() : CarDao

}