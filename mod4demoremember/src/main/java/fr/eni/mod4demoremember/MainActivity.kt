package fr.eni.mod4demoremember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.mod4demoremember.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        Formulaire()
                    }
                }
            }
        }
    }
}

@Composable
fun Formulaire() {
    var nom by rememberSaveable { mutableStateOf("") }
    var prenom by rememberSaveable { mutableStateOf("") }
    var dateNaissance by rememberSaveable { mutableStateOf("") }

    Column {
        TextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom :") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = prenom,
            onValueChange = { prenom = it },
            label = { Text("Prénom :") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = dateNaissance,
            onValueChange = { dateNaissance = it },
            label = { Text("Date de naissance :") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(onClick = { println("coucou") }) {
            Text("Valider")
        }
    }
}






