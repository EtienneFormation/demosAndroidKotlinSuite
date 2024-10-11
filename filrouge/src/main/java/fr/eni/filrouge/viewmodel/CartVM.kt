package fr.eni.filrouge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.eni.filrouge.data.model.Cart
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CartVM(private val cartRepo: CartRepository) : ViewModel() {
    val cart: Flow<Cart> = cartRepo.cartFlow


    fun addToCart(item: Product) {
        viewModelScope.launch {
            cartRepo.addItemInsideCart(item)
        }
    }


    fun removeFromCart(item: Product) {
        viewModelScope.launch {
            cartRepo.removeItemInsideCart(item)
        }
    }
    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartVM(CartRepository.getInstance()) as T
        }
    }
}