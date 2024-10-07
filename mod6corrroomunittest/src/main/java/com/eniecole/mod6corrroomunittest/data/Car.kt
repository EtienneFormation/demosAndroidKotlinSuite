package com.eniecole.mod6corrroomunittest.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val brand: String,
    val model: String,
    val licensePlate: String,
    val price: Int,
    val energy: String
)

