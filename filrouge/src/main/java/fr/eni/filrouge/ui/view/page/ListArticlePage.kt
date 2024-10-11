package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.ui.view.TopBarEniShop
import fr.eni.filrouge.viewmodel.ListProductsVM

@Composable
fun ArticleList(
    navigation : (Int) -> Unit,
    productsViewModel : ListProductsVM = hiltViewModel()
) {
    val productsState = productsViewModel.productsState
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarEniShop()
        }
    ) { innerPadding ->
        Column {
            Categories(productsViewModel.getCategories(), productsViewModel)
            ListContent(productsState.productList, navigation)
        }
    }
}


@Composable
fun ListContent(products : List<Product>, navigation : (Int) -> Unit) {
    LazyVerticalGrid(
        columns =  GridCells.Adaptive(minSize = 102.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(products) {
            ProductCard(it, navigation)
        }
    }
}

@Composable
fun ProductCard(product : Product, navigation : (Int) -> Unit) {
    Surface (
        border = BorderStroke(1.dp, Color.LightGray),
        color = Color.LightGray,
        onClick = { navigation(product.id) }
    )
    {
        Column {
            AsyncImage(
                model = product.url,
                contentDescription = "oui"
            )
            Text(
                text = product.name,
                fontSize = 16.sp
            )
            Text(
                text = "${product.price}€",
                fontSize = 12.sp
            )
        }
    }
}



@Composable
fun Categories(categories: List<String>, productsViewModel : ListProductsVM) {
    val selectedCategory = productsViewModel.productsState.selectedCategory

    LazyRow {
        items(categories) {
            FilterChip(
                onClick = { productsViewModel.selectCategory(it) },
                label = { Text(it) },
                selected = it == selectedCategory
            )
        }
    }
}

