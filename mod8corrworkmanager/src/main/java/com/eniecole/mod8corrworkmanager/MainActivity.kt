package com.eniecole.mod8corrworkmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eniecole.mod8corrworkmanager.ui.theme.Mod3layoutComposeTheme
import com.eniecole.mod8corrworkmanager.vm.ListIncidentsVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListIncidents(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListIncidents(vm : ListIncidentsVM= viewModel(factory = ListIncidentsVM.Factory),
                  name: String, modifier: Modifier = Modifier) {
    if(vm.resultIncidents.value?.results != null){
        LazyColumn {
            items(vm.resultIncidents.value!!.results) {
                Text(text = it.intitule)
            }
        }
    }else{
        Text(text = "Pas de données")
    }

}
