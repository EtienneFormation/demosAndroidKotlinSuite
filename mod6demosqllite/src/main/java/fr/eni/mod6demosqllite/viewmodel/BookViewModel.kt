package fr.eni.mod6demosqllite.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.mod6demosqllite.dal.BookDAO
import fr.eni.mod6demosqllite.data.Book
import fr.eni.mod6demosqllite.dbconfiguration.BookDbHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(val dbHelper : BookDbHelper) : ViewModel() {

    private val _dao = BookDAO(dbHelper.writableDatabase, dbHelper.readableDatabase)

    private val _books = mutableStateOf<List<Book>>(emptyList())
    val books : State<List<Book>> = _books

    init {
        // Permet d'exécuter le code sur un thread secondaire
        // nécessaire pour les opérations sur la DB
        viewModelScope.launch {
            insertBooks(Book(
                0L,
                "Harry Potter et la pierre philosophale",
                "975654321",
                "19/08/2014",
                "Bloomsburry",
                "JK Rowling"
            ))
            insertBooks(Book(
                0L,
                "Harry Potter et la chambre des secrets",
                "975654321",
                "19/08/2014",
                "Bloomsburry",
                "JK Rowling"
            ))
            insertBooks(Book(
                0L,
                "Harry Potter et le prisonnier d'Azkaban",
                "975654321",
                "19/08/2014",
                "Bloomsburry",
                "JK Rowling"
            ))

            loadBooks()
        }

    }

    suspend fun loadBooks() {
        withContext(Dispatchers.IO) {
            val booksFromDB = _dao.getAllBooks()
            _books.value = booksFromDB
        }
    }

    suspend fun insertBooks(book : Book) {
        withContext(Dispatchers.IO) {
            _dao.insertBook(book)
        }
    }

    /*
        Une classe interne utilisée pour expliquer au système Android
        comment il est censé injecter l'instance de BookDBHelper à notre
        viewmodel
     */
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return BookViewModel(
                    BookDbHelper(application.applicationContext),
                ) as T
            }
        }
    }
}