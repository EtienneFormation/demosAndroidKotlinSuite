package fr.eni.mod4corrviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod4corrviewmodel.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod4corrviewmodel.viewmodel.CounterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(vertical = 64.dp)) {
                        Counter(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier, counterViewModel: CounterViewModel = viewModel()) {
    val counter = counterViewModel.counter.collectAsState().value

    Column {
        Text(text = "$counter", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Row (modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1F))
            IconButton(onClick = { counterViewModel.increment() }, modifier = Modifier.weight(1F)) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
            }
            IconButton(onClick = { counterViewModel.decrement() }, modifier = Modifier.weight(1F)) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Decrement")
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}