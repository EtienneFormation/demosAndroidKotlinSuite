package fr.eni.mod4demoviewmodel.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RatingViewModel : ViewModel() {
    private val _rating = MutableStateFlow<Int> (5)
    val rating : StateFlow<Int> = _rating

    fun updateRating(pRating : Int) {
        _rating.value = pRating
    }

}