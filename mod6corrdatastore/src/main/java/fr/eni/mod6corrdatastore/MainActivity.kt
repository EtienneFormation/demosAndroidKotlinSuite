package fr.eni.mod6corrdatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.mod6corrdatastore.ui.theme.Mod3layoutComposeTheme
import fr.eni.mod6corrdatastore.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface (modifier = Modifier.padding(innerPadding)) {
                        TravelForm()
                    }
                }
            }
        }
    }
}

@Composable
fun TravelForm(settingsVM : SettingsViewModel = viewModel ( factory = SettingsViewModel.Factory )) {
    // si vous vous etes plantés comme moi et que vous avez un "null" qui traine dans le code de réduction
    // Petite triche à utiliser une seule fois pour bien avoir une valeur numérique au lieu du null
    // settingsVM.saveReductionCode(0)

    val personalAddress by settingsVM.personalAddress.collectAsState(initial = "")
    val workAddress by settingsVM.workAddress.collectAsState(initial = "")
    val company by settingsVM.company.collectAsState(initial = "")
    val reductionCode by settingsVM.reductionCode.collectAsState(initial = 0)

    Column {
        TextField(
            value = personalAddress ?: "",
            onValueChange = { settingsVM.savePersonalAddress(it) },
            label = { Text("Adresse personnelle") }
        )

        TextField(
            value = workAddress ?: "",
            onValueChange = { settingsVM.saveWorkAddress(it) },
            label = { Text("Adresse professionnelle") }
        )

        TextField(
            value = company ?: "",
            onValueChange = { settingsVM.saveCompany(it) },
            label = { Text("Code entreprise") }
        )

        TextField(
            value = "$reductionCode",
            onValueChange = { settingsVM.saveReductionCode(it.toInt()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("ID Carte réduction") }
        )
    }
}