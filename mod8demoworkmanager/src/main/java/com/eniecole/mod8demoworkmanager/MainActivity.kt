package com.eniecole.mod8demoworkmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eniecole.mod8demoworkmanager.ui.theme.Mod3layoutComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SyncDbInfo(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SyncDbInfo(viewModel: ListThingsViewModel = viewModel(factory = ListThingsViewModel.Factory),
               name: String, modifier: Modifier = Modifier) {
    Text(
        text = when(viewModel.resultDbSync.value) {
            null->"Info Sync : Pas encore lancée"
            true->"Info Sync : Done !"
            false->"Info Sync : Erreur"
        },
        modifier = modifier
    )
}
