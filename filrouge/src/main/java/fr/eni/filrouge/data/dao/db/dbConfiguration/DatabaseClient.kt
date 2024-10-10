package fr.eni.filrouge.data.dao.db.dbConfiguration

import android.content.Context
import androidx.room.Room

class DatabaseClient(context: Context) {
    val appDatabase : AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "enishop.db"
    )
    .fallbackToDestructiveMigration()
    .build()
}