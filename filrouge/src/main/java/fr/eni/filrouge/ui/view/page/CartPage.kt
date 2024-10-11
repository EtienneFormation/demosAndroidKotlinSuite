package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.filrouge.data.model.Cart
import fr.eni.filrouge.ui.view.components.TopBarEniShop
import fr.eni.filrouge.viewmodel.CartVM

@Composable
fun CartPage(modifier: Modifier = Modifier,
             vm : CartVM = viewModel(factory = CartVM.Factory),
             navigationToCart : () -> Unit,
) {
    val listProducts = vm.cart.collectAsState(Cart()).value.listProducts
    Scaffold(
        topBar = { TopBarEniShop(navigationToCart) }
    ) { innerPadding ->
        LazyColumn(Modifier.padding(innerPadding)) {
            items(listProducts){
                Text(it.name)
            }
        }
    }
}