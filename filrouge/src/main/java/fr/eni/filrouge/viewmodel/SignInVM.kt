package fr.eni.filrouge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInVM : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(
            email = email,
            isEmailValid = isValidEmail(email)
        ) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(
            password = password,
            isPasswordValid = password.length >= 6
        ) }
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            // Ici, vous pourriez appeler un repository pour effectuer la connexion
            _uiState.update{
                it.copy(isPasswordValid = true, isEmailValid = true);
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true
)

