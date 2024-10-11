package fr.eni.filrouge.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.eni.filrouge.data.repository.CartRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TopAppBarVM @Inject constructor(
    cartRepository: CartRepository
) : ViewModel(){
    val nbOfItemsInCart = cartRepository.cartFlow.map { it.size }
}