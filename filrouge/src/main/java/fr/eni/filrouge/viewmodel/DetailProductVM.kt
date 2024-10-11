package fr.eni.filrouge.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.repository.CartRepository
import fr.eni.filrouge.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//TODO Créer le viewmodel avec l'appel getById
@HiltViewModel
class DetailProductVM @Inject constructor(val repoProduct: ProductRepository,
                                          val repoCart : CartRepository
) : ViewModel() {
    var productState by mutableStateOf<Product?>(null)
        private set


    fun addInsideCart(product: Product){
        viewModelScope.launch {
            repoCart.addItemInsideCart(product)
        }
    }


    fun getById(id : Int)  {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(repoProduct.fetchProducts().isEmpty()){
                    productState = null
                }
                productState = repoProduct.fetchProducts().find { it.id == id }
            }
        }
    }
}
