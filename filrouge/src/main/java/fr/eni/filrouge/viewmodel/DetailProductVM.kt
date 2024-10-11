package fr.eni.filrouge.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO Créer le viewmodel avec l'appel getById
class DetailProductVM(val repoProduct: ProductRepository,
                      /*val repoCart : CartRepository*/) : ViewModel() {
    var productState by mutableStateOf<Product?>(null)
        private set

    //fun addInsideCart(product: Product){
    //    viewModelScope.launch {
    //        repoCart.addItemInsideCart(product)
    //    }
    //}

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

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return DetailProductVM(
                    ProductRepository(application),
                    //CartRepository.getInstance()
                ) as T
            }
        }
    }
}