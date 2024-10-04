package fr.eni.mod6demoroom.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.eni.mod6demoroom.data.Music

@Dao
interface MusicDao {
    @Insert
    suspend fun insert(music : Music)

    @Query("SELECT * FROM music")
    fun getAllMusics() : List<Music>
}
