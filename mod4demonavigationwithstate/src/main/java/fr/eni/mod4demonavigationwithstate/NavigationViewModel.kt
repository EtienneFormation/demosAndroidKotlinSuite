package fr.eni.mod4demonavigationwithstate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : ViewModel() {
    private val _applicationState = MutableStateFlow<ApplicationState>(ApplicationState.LandingPage)
    val applicationState : StateFlow<ApplicationState> = _applicationState

    fun navigateTo(destination : ApplicationState) {
        _applicationState.value = destination
    }
}

sealed class ApplicationState {
    data object LandingPage : ApplicationState()
    data object LoginPage : ApplicationState()
    data object ContentPage : ApplicationState()
}