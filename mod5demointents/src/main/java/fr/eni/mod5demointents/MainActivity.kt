package fr.eni.mod5demointents

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.mod5demointents.ui.theme.Mod3layoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val context = this

        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface ( Modifier.padding(innerPadding)) {
                        Column {
                            Text("Vous êtes sur la page d'accueil")
                            OutlinedButton(
                                onClick = {
                                    // intent explicite
                                    val intent = Intent(context, DestinationActivity::class.java)
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("Naviguer")
                            }

                            OutlinedButton(
                                onClick = {
                                    // intent implicite
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:0606060606"))
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("Consulter un SMS")
                            }
                        }
                    }
                }
            }
        }
    }
}
