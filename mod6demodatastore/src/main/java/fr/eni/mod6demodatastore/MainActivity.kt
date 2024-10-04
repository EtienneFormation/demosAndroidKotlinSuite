package fr.eni.mod6demodatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod6demodatastore.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod6demodatastore.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
) {
    val primaryColor by settingsViewModel.primaryColor.collectAsState(initial = "")
    val darkMode by settingsViewModel.darkModeEnabled.collectAsState(initial = false)

    Column {
        TextField(
            value = primaryColor ?: "",
            onValueChange = { settingsViewModel.savePrimaryColor(it) },
            label = { Text("Couleur primaire") }
        )

        Text("Mode Sombre")
        Switch(
            checked = darkMode ?: false,
            onCheckedChange = { settingsViewModel.saveDarkMode(it) }
        )

        Surface (
            color = if (darkMode == true) Color.DarkGray else Color.LightGray,
            contentColor = if (darkMode == true) Color.LightGray else Color.DarkGray
        )
        {
            Text("Test")
        }
    }


}