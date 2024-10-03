package fr.eni.mod6demosqllite.dbconfiguration

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import fr.eni.mod6demosqllite.contract.BookContract
import fr.eni.mod6demosqllite.contract.DBContract

/*
    La classe qui nous permet de définir le comportement de la BDD
    lorsque l'application se crée et que la BDD est créée ou mise à jour.
 */
class BookDbHelper(context : Context) : SQLiteOpenHelper(
    context,
    DBContract.DB_NAME,
    null,
    DBContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(BookContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(BookContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}