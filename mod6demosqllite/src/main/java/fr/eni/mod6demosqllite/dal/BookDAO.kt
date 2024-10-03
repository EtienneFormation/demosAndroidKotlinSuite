package fr.eni.mod6demosqllite.dal

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import fr.eni.mod6demosqllite.contract.BookContract
import fr.eni.mod6demosqllite.data.Book

/*
    SQLite distingue le dbWriter du dbReader
 */
class BookDAO(val dbW : SQLiteDatabase, val dbR : SQLiteDatabase) {

    fun insertBook(book : Book) : Boolean {
        val values = ContentValues().apply {
            put (BookContract.COLUMN_NAME, book.name)
            put (BookContract.COLUMN_ISBN, book.isbn)
            put (BookContract.COLUMN_RELEASE, book.releaseDate)
            put (BookContract.COLUMN_EDITOR, book.editor)
            put (BookContract.COLUMN_AUTHOR, book.author)
        }

        val newId = dbW.insert(BookContract.TABLE_NAME, null, values)
        return newId != -1L
    }

    fun getAllBooks() : List<Book> {
        val projection = arrayOf(
            BaseColumns._ID,
            BookContract.COLUMN_NAME,
            BookContract.COLUMN_ISBN,
            BookContract.COLUMN_RELEASE,
            BookContract.COLUMN_EDITOR,
            BookContract.COLUMN_AUTHOR,
        )

        val cursor = dbR.query(
            BookContract.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val books = mutableListOf<Book>()

        // indique que les fonctions appelées dans ce bloc se font sur l'instance de cursor
        with(cursor) {
            while (moveToNext()) {
                val book = Book (
                    getLong(getColumnIndexOrThrow(BaseColumns._ID)),
                    getString(getColumnIndexOrThrow(BookContract.COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(BookContract.COLUMN_ISBN)),
                    getString(getColumnIndexOrThrow(BookContract.COLUMN_RELEASE)),
                    getString(getColumnIndexOrThrow(BookContract.COLUMN_EDITOR)),
                    getString(getColumnIndexOrThrow(BookContract.COLUMN_AUTHOR)),
                )

                books += book
            }

            close()
        }

        return books
    }

}