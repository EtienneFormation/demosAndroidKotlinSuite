package fr.eni.mod3correction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eni.mod3correction.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(16.dp))
                {
                    innerPadding ->
                    ItemDetails(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ItemDetails(modifier: Modifier = Modifier) {
    Surface (
        modifier = modifier
    ) {
        Column {
            Text(
                text = "Un petit chat trop mignon",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "0€",
                fontWeight = FontWeight.Bold
            )
            Text(text = "ou 0€/mois")
            Spacer(Modifier.padding(vertical = 8.dp))
            Image(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Une image de chat",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.padding(vertical = 8.dp))
            Title("Description")
            Text(text = "Ce petit chat trop mignon vous aidera à vous réveiller durant cette " +
                    "présentation matinale.")
            Title("Caractéristiques")
            Text(text = "Petit")
            Text(text = "Trop mignon")
            Text(text = "Blanc et gris")
            Text(text = "Yeux jaune")
            Text(text = "Moustaches")
        }
    }
}

@Composable
fun Title(text : String) {
    Spacer(Modifier.padding(vertical = 6.dp))
    Text(
        text = text,
        fontSize = 24.sp
    )
    Spacer(Modifier.padding(vertical = 6.dp))
}