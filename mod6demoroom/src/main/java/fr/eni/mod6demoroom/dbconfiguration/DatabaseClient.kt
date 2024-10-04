package fr.eni.mod6demoroom.dbconfiguration

import android.content.Context
import androidx.room.Room

class DatabaseClient(context: Context) {
    val appDatabase : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "unptinom.db"
    ).build()
}