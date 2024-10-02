package fr.eni.mod5demotestscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.mod5demotestscompose.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PasswordField(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

const val TEST_TAG_TEXTFIELD_PWD = "TextFieldPassword"

@Composable
fun PasswordField(modifier: Modifier) {
    var password by rememberSaveable { mutableStateOf("") }
    var isVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .testTag(TEST_TAG_TEXTFIELD_PWD),
        value = password,
        onValueChange = { password = it },
        label = { Text("Password : ") },
        singleLine = true,
        visualTransformation =
            if (isVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image =
                if (isVisible) Icons.Filled.Done
                else Icons.Filled.Close

            val description =
                if (isVisible) "Cacher mot de passe"
                else "Montrer mot de passe"

            IconButton(
                onClick = { isVisible = !isVisible }
            ) {
                Icon(image, description)
            }
        }
    )
}