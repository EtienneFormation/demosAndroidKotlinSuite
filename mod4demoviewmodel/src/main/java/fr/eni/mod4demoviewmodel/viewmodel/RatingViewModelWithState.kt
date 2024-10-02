package fr.eni.mod4demoviewmodel.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RatingViewModelWithState : ViewModel() {
    private val _state = MutableStateFlow<RatingState>(RatingState.Initial())
    val state : StateFlow<RatingState> =_state

    fun updateRating(rating : Int) {
        if (rating > 8)
            _state.value = RatingState.Success(rating)
        else
            _state.value = RatingState.RequestFeedback(rating)
    }
}

// Une classe sealed fonctionne comme open sauf que seules les
// les classes définies directement dedans ont le droit d'en hériter
// Design pattern "Machine à état"
sealed class RatingState(var rating : Int? = null) {
    class Initial : RatingState()
    class Success(rating : Int?) : RatingState(rating)
    class RequestFeedback(rating : Int?) : RatingState(rating)
}
