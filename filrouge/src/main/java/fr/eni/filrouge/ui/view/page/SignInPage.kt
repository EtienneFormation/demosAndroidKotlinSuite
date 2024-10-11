package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.filrouge.ui.view.components.TopBarEniShop
import fr.eni.filrouge.viewmodel.SignInVM

@Composable
fun SignInPage(modifier: Modifier = Modifier,
               vm: SignInVM = viewModel(),
               navigationToList : () -> Unit,
   ) {
    val uiState by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopBarEniShop(null)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Champ email
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { vm.onEmailChanged(it) },
                label = { Text("Email") },
                isError = !uiState.isEmailValid,
                modifier = Modifier.fillMaxWidth()
            )
            //Message erruer email
            if (!uiState.isEmailValid) {
                Text(
                    text = "Email invalide",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            //Champs password
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { vm.onPasswordChanged(it) },
                label = { Text("Mot de passe") },
                visualTransformation = PasswordVisualTransformation(),
                isError = !uiState.isPasswordValid,
                modifier = Modifier.fillMaxWidth()
            )
            //Message erreur password
            if (!uiState.isPasswordValid) {
                Text(
                    text = "Le mot de passe doit contenir au moins 6 caractères",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                onClick = {
                    vm.onLoginClicked()
                    navigationToList()
                },
                enabled = uiState.isEmailValid && uiState.isPasswordValid &&
                        uiState.email.isNotEmpty() && uiState.password.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Se connecter")
            }
        }
    }
}