package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import fr.eni.filrouge.ui.view.TopBarEniShop
import fr.eni.filrouge.viewmodel.DetailProductVM


@Composable
fun ArticleDetail(id : Int, productVM : DetailProductVM = hiltViewModel()) {
    productVM.getById(id)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarEniShop()
        }
    ) { innerPadding ->
        if (productVM.productState == null) {
            Text("Pac encore Chagé")
        } else
            Surface {
                Column {
                    Text(
                        text = productVM.productState!!.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${productVM.productState!!.price}€",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.padding(vertical = 8.dp))
                    AsyncImage(
                        model = productVM.productState!!.url,
                        contentDescription = "oui",
                        modifier = Modifier.height(90.dp)
                    )
                    Spacer(Modifier.padding(vertical = 8.dp))
                    Title("Description")
                    Text(productVM.productState!!.description)
                    Title("Caractéristiques")
                    LazyColumn {
                        items(productVM.productState!!.characteristics) {
                            Text(it)
                        }
                    }
                    //Créer un espace entre deux "morceaux" de colonnes
                    Spacer(Modifier.weight(1f))
                    ElevatedButton(modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp),
                        onClick = { productVM.addInsideCart(productVM.productState!!) }) { Text("Ajouter au panier") }
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
