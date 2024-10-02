package fr.eni.mod5demonavigation

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.eni.mod5demonavigation.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Menu(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Menu(
    modifier: Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination : String = "signin"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination =  startDestination
    ) {
        composable("signin") {
            SignInPage(onClickToHome = { navController.navigate("home") })
        }
        composable("home") {
            HomePage()
        }
    }
}

@Composable
fun HomePage() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Bienvenue sur votre espace"
    )
}

@Composable
/*
    La signature "() -> Unit" caractérise une fonction
        ne prenant aucun paramètre (exprimé par le "()")
        ne restituant aucun résultat (exprimé par le Unit)
 */
fun SignInPage(onClickToHome : () -> Unit) {
    Column {
        Text("Connectez-vous")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Steeve Travail",
            onValueChange = {}
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Tony stark",
            onValueChange = {}
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickToHome
        ) {
            Text("se connecter")
        }
    }
}















