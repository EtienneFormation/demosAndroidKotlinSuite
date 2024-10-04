package fr.eni.mod6demoroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod6demoroom.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod6demoroom.viewmodel.MusicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface (modifier = Modifier.padding(innerPadding)) {
                        MusicScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MusicScreen(musicVM : MusicViewModel = viewModel(factory = MusicViewModel.Factory)) {
    val musics = musicVM.musics.value

    LazyColumn {
        items(musics) {
            Text("Name : ${it.title}, author : ${it.author}")
        }
    }
}