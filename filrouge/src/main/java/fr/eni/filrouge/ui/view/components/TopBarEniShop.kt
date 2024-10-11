package fr.eni.filrouge.ui.view.components

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
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.filrouge.viewmodel.TopBarVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarEniShop( onCartClick:(()->Unit)?,vm : TopBarVM = viewModel(factory = TopBarVM.Factory)) {
    val sizeCart = vm.cart.collectAsState().value.listProducts.size
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text("ENI Shop")
        },
        actions = {
            if(onCartClick != null)
            BadgedBox(badge = { Badge{Text("$sizeCart") }})  {
                IconButton(onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}