package fr.eni.filrouge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.ui.theme.Mod3layoutComposeTheme
import fr.eni.filrouge.viewmodel.ListProductsVM

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod3layoutComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
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
                                    Icon(
                                        imageVector = Icons.Outlined.ShoppingCart,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Menu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Menu(
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination : String = "articles"
) {
    Surface  {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("articles") {
                ArticleList(navigation = {id -> navController.navigate("detail/${id}")
                })
            }
            composable("detail/{id}") { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("id")!!.toInt()

                ArticleDetail(id)
            }
        }
    }
}

@Composable
fun ArticleList(
    navigation : (Int) -> Unit,
    productsViewModel : ListProductsVM = viewModel(factory = ListProductsVM.Factory)
) {
    val productsState = productsViewModel.productState.collectAsState().value

    Column {
        Categories(productsViewModel.getCategories(), productsViewModel)
        ListContent(productsState.productList, navigation)
    }
}

@Composable
fun Categories(categories: List<String>, productsViewModel : ListProductsVM) {
    val selectedCategory = productsViewModel.productState.collectAsState().value.selectedCategory

    LazyRow() {
        items(categories) {
            FilterChip(
                onClick = { productsViewModel.selectCategory(it) },
                label = { Text(it) },
                selected = it == selectedCategory
            )
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
fun ArticleDetail(id : Int, productsViewModel : ListProductsVM =
    viewModel(factory = ListProductsVM.Factory)) {
    val article = productsViewModel.getById(id)
    if(article==null){
        Text("Pac encore Chagé")
    }else
        Surface {
            Column {
                Text(
                    text = article.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${article.price}€",
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                AsyncImage(
                    model = article.url,
                    contentDescription = "oui",
                    modifier = Modifier.height(90.dp)
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                Title("Description")
                Text(article.description)
                Title("Caractéristiques")
                LazyColumn {
                    items(article.characteristics) {
                        Text(it)
                    }
                }
            }
        }
}

@Composable
fun Title(text : String) {
    Spacer(Modifier.padding(vertical = 6.dp))
    Text(
        text = text,
        fontSize = 24.sp
    )
    Spacer(Modifier.padding(vertical = 6.dp))
}
