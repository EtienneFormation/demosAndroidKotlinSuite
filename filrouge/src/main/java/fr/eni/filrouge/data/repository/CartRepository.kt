package fr.eni.filrouge.data.repository

import fr.eni.filrouge.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepository(){
    private val _cartFlow = MutableStateFlow(emptyList<Product>())
    val cartFlow: Flow<List<Product>> = _cartFlow.asStateFlow()


    fun addItemInsideCart(item: Product) {
        _cartFlow.value += item
        // Ici, vous pourriez avoir une logique pour sauvegarder l'état du panier
    }

    fun removeItemInsideCart(item: Product) {
        _cartFlow.value -= item
    }
    //Double locking check singleton
    //companion object {
    //    @Volatile
    //    private var _instance: CartRepository? = null
    //    fun getInstance(): CartRepository {
    //        return _instance ?: synchronized(this) {
    //            _instance ?: CartRepository().also { _instance = it }
    //        }
    //    }
    //}
}