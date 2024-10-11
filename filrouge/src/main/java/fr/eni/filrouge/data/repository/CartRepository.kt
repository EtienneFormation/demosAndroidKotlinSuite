package fr.eni.filrouge.data.repository

import fr.eni.filrouge.data.model.Cart
import fr.eni.filrouge.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepository private constructor(){
    private val _cartFlow = MutableStateFlow(Cart(emptyList()))
    val cartFlow: Flow<Cart> = _cartFlow.asStateFlow()

    fun getItemsInsideCart(): List<Product> {
        // Ici, vous pourriez avoir une logique pour charger les items depuis une source de données
        return _cartFlow.value.listProducts
    }

    fun addItemInsideCart(item: Product) {
        val currentList = _cartFlow.value.listProducts
        _cartFlow.value = Cart(currentList + item)
        // Ici, vous pourriez avoir une logique pour sauvegarder l'état du panier
    }

    fun removeItemInsideCart(item: Product) {
        val currentList = _cartFlow.value.listProducts
        _cartFlow.value = Cart(currentList - item)
        // Ici, vous pourriez avoir une logique pour sauvegarder l'état du panier
    }

    companion object {
        @Volatile
        private var instance: CartRepository? = null

        fun getInstance(): CartRepository {
            return instance ?: synchronized(this) {
                instance ?: CartRepository().also { instance = it }
            }
        }
    }

}