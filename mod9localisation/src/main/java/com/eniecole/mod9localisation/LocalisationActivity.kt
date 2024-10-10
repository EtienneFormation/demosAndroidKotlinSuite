package com.eniecole.mod9localisation

import android.Manifest
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eniecole.mod9localisation.ui.theme.Mod3layoutComposeTheme
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

private const val TAG = "LocalisationActivity"
class LocalisationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geolocation()
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun geolocation() {
        //On créé le client de localisation
        val fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        //On créé le contrat de demande de permission explicite à l'utilisateur
        val locationRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { grantedPermissions ->
            //Si les duex permissions ont été acceptées
            if (grantedPermissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                grantedPermissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                //Déclncher un cycle d'écoute des localisations toutes les 5 secondes
                fusedLocation.requestLocationUpdates(
                    LocationRequest.Builder(5000).build(),
                    { location ->
                        //Lorsque que la position est récupérée afficher un log
                        Log.i(TAG, "onCreate: lat${location.latitude} lng${location.longitude}")
                    },
                    Looper.getMainLooper()
                )
            }
        }
        locationRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mod3layoutComposeTheme {
        Greeting("Android")
    }
}