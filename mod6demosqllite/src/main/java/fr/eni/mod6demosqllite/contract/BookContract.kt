package fr.eni.mod6demosqllite.contract

import android.provider.BaseColumns

/*
    La classe qui contient les noms de tables et de colonnes
    Ainsi que les requêtes SQL pour interagir avec la table "books"
 */
object BookContract {
    const val TABLE_NAME = "books"

    const val COLUMN_NAME = "name"
    const val COLUMN_ISBN = "isbn"
    const val COLUMN_RELEASE = "release"
    const val COLUMN_EDITOR = "editor"
    const val COLUMN_AUTHOR = "author"

    const val SQL_CREATE_ENTRIES = """
        CREATE TABLE $TABLE_NAME (
             ${BaseColumns._ID} INTEGER PRIMARY KEY,
             $COLUMN_NAME TEXT,
             $COLUMN_ISBN TEXT,
             $COLUMN_RELEASE TEXT,
             $COLUMN_EDITOR TEXT,
             $COLUMN_AUTHOR TEXT
        )
    """

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}