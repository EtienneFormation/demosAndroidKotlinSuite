package fr.eni.mod6demosqllite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod6demosqllite.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod6demosqllite.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Spacer(Modifier.padding(innerPadding))
                    BookScreen()
                }
            }
        }
    }
}

@Composable
fun BookScreen(bookVM : BookViewModel = viewModel(factory = BookViewModel.Factory)) {
    // asynchrone
    val books by bookVM.books

    LazyColumn {
        items(books) {
            Text("Name : ${it.name}, author : ${it.author}")
        }
    }
}