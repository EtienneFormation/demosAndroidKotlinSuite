package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInPage(navigationToArticles : () -> Unit,
               modifier: Modifier = Modifier) {
    var stateEmail by remember { mutableStateOf("") }
    var stateEmailError by remember { mutableStateOf("") }
    var statePwd by remember { mutableStateOf("") }
    Column {
        //TODO Faire la page de connexion
        //Champ Email (TextField)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
            value = stateEmail,
            isError = stateEmailError.isNotEmpty(),
            onValueChange = {
                stateEmail = it
                if(!stateEmail.contains("@")){
                    stateEmailError = "L'email doit contenir @"
                }else{stateEmailError = "" }
            },
            label = { Text("Email") }
        )
        if(stateEmailError.isNotEmpty())
            Text("Erreur $stateEmailError",color= MaterialTheme.colorScheme.error)
        //Text error is erreur présente
        //Champ Password avec PasswordVisualisation
        //Text error si erreur présente
        Modifier.weight(1f)
        ElevatedButton(onClick = navigationToArticles) {
            Text("Se Connecter")
        }
    }
}