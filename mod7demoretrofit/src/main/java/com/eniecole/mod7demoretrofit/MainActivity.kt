package com.eniecole.mod7demoretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eniecole.mod7demoretrofit.ui.theme.Mod3layoutComposeTheme
import com.eniecole.mod7demoretrofit.vm.ListWeatherVM


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize().padding(8.dp)) { innerPadding ->
                    WeatherScreen(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun WeatherScreen(modifier : Modifier,viewModel : ListWeatherVM = viewModel()){
    val weatherData by viewModel.weather
    LazyColumn {
        weatherData?.let {
            itemsIndexed(it.time) { index, time ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = time)
                    Text(text = "${it.temperature_2m[index]}°C")
                } }
        } }
}