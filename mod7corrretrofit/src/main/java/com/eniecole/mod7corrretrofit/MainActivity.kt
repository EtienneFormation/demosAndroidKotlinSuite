package com.eniecole.mod7corrretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery0Bar
import androidx.compose.material.icons.filled.Battery1Bar
import androidx.compose.material.icons.filled.Battery2Bar
import androidx.compose.material.icons.filled.Battery3Bar
import androidx.compose.material.icons.filled.Battery4Bar
import androidx.compose.material.icons.filled.Battery5Bar
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.ElectricScooter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eniecole.mod7corrretrofit.ui.theme.Mod3layoutComposeTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListPonies(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListPonies(modifier: Modifier = Modifier,vm: ListPonyVM = viewModel()) {
    val listBikes=  vm.ponies.value?.bikes
    if(listBikes == null){
        Text("Liste en cours de chargement")
    }
    else
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            itemsIndexed(listBikes){index, item ->
                Card(colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                        Row(Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                            Icon( Icons.Default.ElectricScooter,
                                contentDescription = "Trotinette Électrique")
                            Text("${item.current_fuel_percent?.times(100)?.roundToInt()}%")
                            Spacer(Modifier.weight(1f))
                            when(item.current_fuel_percent ?: 0f){
                                in 0.0..<0.125 ->Icon(Icons.Default.Battery0Bar,
                                    contentDescription = "Batterie Vide")
                                in 0.125..<0.25 ->Icon(Icons.Default.Battery1Bar,
                                    contentDescription = "Moins de 25%")
                                in 0.25..<0.375 ->Icon(Icons.Default.Battery2Bar,
                                    contentDescription = "Moins de 37%")
                                in 0.375..<0.5 ->Icon(Icons.Default.Battery3Bar,
                                    contentDescription = "Moins de 50%")
                                in 0.5..<0.625 ->Icon(Icons.Default.Battery4Bar,
                                    contentDescription = "Moins de 62%")
                                in 0.625..<0.75 ->Icon(Icons.Default.Battery5Bar,
                                    contentDescription = "Moins de 75%")
                                in 0.75..<0.875 ->Icon(Icons.Default.Battery6Bar,
                                    contentDescription = "Moins de 87%")
                                in 0.875..1.0 ->Icon(Icons.Default.BatteryFull,
                                    contentDescription = "Batterie Pleine")
                            }

                            Text("${item.current_range_meters?.div(1000)}km")
                        }
                    }

            }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mod3layoutComposeTheme {
        ListPonies()
    }
}