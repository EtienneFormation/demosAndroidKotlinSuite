package fr.eni.filrouge.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.eni.filrouge.ui.theme.Mod3layoutComposeTheme
import fr.eni.filrouge.ui.view.page.ArticleDetail
import fr.eni.filrouge.ui.view.page.ArticleList
import fr.eni.filrouge.ui.view.page.SignInPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Menu()
            }
        }
    }


}


@Composable
fun Menu(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination : String = "signInPage"
) {
    Surface  {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("articles") {
                ArticleList(navigation = {id -> navController.navigate("detail/${id}")
                })
            }
            composable("signInPage") {
                SignInPage(navigationToArticles = {navController.navigate("articles")
                })
            }
            composable("detail/{id}") { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("id")!!.toInt()

                ArticleDetail(id)
            }
        }
    }
}


