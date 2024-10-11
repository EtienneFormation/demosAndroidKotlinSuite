package fr.eni.filrouge.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import fr.eni.filrouge.viewmodel.TopAppBarVM

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarEniShop(vm : TopAppBarVM = hiltViewModel()) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text("ENI Shop")
        },
        actions = {
            IconButton(onClick = { println("Le panier !") }) {
                BadgedBox(
                    badge = {Badge {
                        Text("${vm.nbOfItemsInCart.collectAsState(0).value}")
                    }}) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}