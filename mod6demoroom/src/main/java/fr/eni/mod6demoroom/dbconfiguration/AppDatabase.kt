package fr.eni.mod6demoroom.dbconfiguration

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.eni.mod6demoroom.data.Music
import fr.eni.mod6demoroom.repositories.MusicDao

@Database(entities = [Music::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao() : MusicDao
}