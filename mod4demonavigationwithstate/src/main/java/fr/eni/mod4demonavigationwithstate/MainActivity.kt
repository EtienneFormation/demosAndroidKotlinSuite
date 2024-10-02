package fr.eni.mod4demonavigationwithstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod4demonavigationwithstate.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ApplicationMenu(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ApplicationMenu(modifier: Modifier, navigationViewModel: NavigationViewModel = viewModel()) {
    val state = navigationViewModel.applicationState.collectAsState().value

    Column (Modifier.fillMaxSize()) {
        Row (Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { navigationViewModel.navigateTo(ApplicationState.LandingPage) },
                Modifier.weight(1f)
            ) {
                Text("Accueil")
            }
            OutlinedButton(
                onClick = { navigationViewModel.navigateTo(ApplicationState.LoginPage) },
                Modifier.weight(1f)
            ) {
                Text("Connexion")
            }
            OutlinedButton(
                onClick = { navigationViewModel.navigateTo(ApplicationState.ContentPage) },
                Modifier.weight(1f)
            ) {
                Text("Contenu")
            }
        }

        when (state) {
            ApplicationState.LandingPage -> LandingPage()
            ApplicationState.LoginPage -> LoginPage()
            ApplicationState.ContentPage -> ContentPage()
        }
    }
}

@Composable
fun LandingPage() {
    Text("Vous êtes sur la page d'accueil")
}

@Composable
fun LoginPage() {
    Text("Vous êtes sur la page de connexion")
}

@Composable
fun ContentPage() {
    Text("Vous êtes sur la page du contenu")
}