package fr.eni.filrouge.ui.view.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.eni.filrouge.ui.view.components.TopBarEniShop
import fr.eni.filrouge.viewmodel.DetailProductVM


@Composable
fun ArticleDetail(id : Int, navigationToCart: ()->Unit,vm : DetailProductVM =
    viewModel(factory = DetailProductVM.Factory)
) {
    vm.getById(id)
    val article = vm.productState
    Scaffold(
        topBar = { TopBarEniShop(navigationToCart) }
    ) { innerPadding ->
        if(article==null){
            Text(modifier = Modifier.padding(innerPadding),text ="Pac encore Chagé")
        }else
            Surface {
                Column(Modifier.padding(8.dp)) {
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
                    //Créer un espace entre deux "morceaux" de colonnes
                    Spacer( modifier = Modifier.weight(1f))
                    ElevatedButton(onClick = { vm.addInsideCart(product =article) }
                        ,
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)) {
                        Text("Ajouter au panier")
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
