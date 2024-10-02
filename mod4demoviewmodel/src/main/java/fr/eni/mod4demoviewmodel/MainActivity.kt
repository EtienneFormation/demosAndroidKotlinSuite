package fr.eni.mod4demoviewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod4demoviewmodel.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod4demoviewmodel.viewmodel.RatingState
import fr.eni.mod4demoviewmodel.viewmodel.RatingViewModel
import fr.eni.mod4demoviewmodel.viewmodel.RatingViewModelWithState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Column {
                    RatingScreen()
                    RatingScreenWithState()
                }

            }
        }
    }
}

@Composable
fun RatingScreen(ratingViewModel : RatingViewModel = viewModel()) {
    val rating = ratingViewModel.rating.collectAsState().value

    Column {
        Slider(
            value = rating.toFloat(),
            steps = 100,
            valueRange = 0F..10F,
            onValueChange = {
                ratingViewModel.updateRating(it.toInt())
            }
        )
        if (rating < 8) {
            Text("Comment pouvons-nous vous aider ?")
        } else {
            Text("Nous sommes ravis que vous soyez satisfait")
        }
    }
}

@Composable
fun RatingScreenWithState(ratingViewModel : RatingViewModelWithState = viewModel()) {
    val state = ratingViewModel.state.collectAsState().value

    Column {
        Slider(
            value = state.rating?.toFloat() ?: 5F,
            steps = 100,
            valueRange = 0F..10F,
            onValueChange = {
                ratingViewModel.updateRating(it.toInt())
            }
        )
        if (state is RatingState.RequestFeedback) {
            Text("Comment pouvons-nous vous aider ?")
        } else if (state is RatingState.Success) {
            Text("Nous sommes ravis que vous soyez satisfait")
        } else {
            Text("Veuillez saisir votre niveau de satisfaction")
        }
    }
}
