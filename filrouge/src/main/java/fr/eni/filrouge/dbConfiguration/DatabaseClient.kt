package fr.eni.filrouge.dbConfiguration

import android.content.Context
import androidx.room.Room

class DatabaseClient(context: Context) {
    val appDatabase : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "enishop.db"
    ).build()
}