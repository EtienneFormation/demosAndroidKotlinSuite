package fr.eni.filrouge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.eni.filrouge.data.model.Cart
import fr.eni.filrouge.data.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TopBarVM (private val cartRepo: CartRepository) : ViewModel() {
    val cart: StateFlow<Cart> = cartRepo.cartFlow.stateIn(viewModelScope, SharingStarted.Eagerly, Cart())

    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopBarVM(CartRepository.getInstance()) as T
        }
    }

}