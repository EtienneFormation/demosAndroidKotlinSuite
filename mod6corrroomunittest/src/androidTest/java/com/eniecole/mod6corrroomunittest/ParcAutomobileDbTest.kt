package com.eniecole.mod6corrroomunittest;

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eniecole.mod6corrroomunittest.data.Car
import com.eniecole.mod6corrroomunittest.dbconfiguration.AppDatabase
import com.eniecole.mod6corrroomunittest.repositories.CarDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParcAutomobileDbTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: CarDao

    @Before
    fun setup() {
    //Récupérer ici votre instance de DB et de DAO
    //grâce à la librairie de test Room
    database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    ).build()
    dao = database.carDao()
    }

    @After
    fun tearDown() {
        //Fermez ici votre base de données
        database.close()
    }

    @Test
    fun writeAndReadYourEntity() = runBlocking {
        val megane = Car(0L,"Renault","Megane E-Tech","GG-123-AZ", 35000,"EL")
        val twingo = Car(0L,"Renault","Twingo E-Tech","GA-421-SA", 25000,"EL")

        val idMegane =  dao.insert(megane)
        val idTwingo = dao.insert(twingo)
        assert(idMegane == 1L)
        assert(idTwingo == 2L)
        //Insérez et verifiez que la base de données possède bien vos 2 voitures
    }
}
