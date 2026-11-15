package com.dam.cafepunto.ui.login

import androidx.lifecycle.ViewModel
import com.dam.cafepunto.R
import com.dam.cafepunto.data.UsuarioRepositorio
import com.dam.cafepunto.data.Validador
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(evento: LoginUiEvent) {
        when (evento) {
            is LoginUiEvent.CorreoCambiado ->
                _uiState.update { validarCampos(it.copy(correo = evento.correo)) }
            is LoginUiEvent.ContrasenaCambiada ->
                _uiState.update { validarCampos(it.copy(contrasena = evento.contrasena)) }
            LoginUiEvent.BotonLoginPulsado -> login()
            LoginUiEvent.MensajeMostrado -> _uiState.update { it.copy(mensajeToast = null) }
            LoginUiEvent.NavegacionRealizada -> _uiState.update { it.copy(loginCorrecto = false) }
        }
    }

    // Valida los campos y solo habilita el botón si ambos son correctos
    private fun validarCampos(estado: LoginUiState): LoginUiState {
        val correoValido = Validador.esCorreoValido(estado.correo)
        val contrasenaValida = Validador.esContrasenaValida(estado.contrasena)
        return estado.copy(
            errorCorreo = if (estado.correo.isNotEmpty() && !correoValido) R.string.error_correo else null,
            errorContrasena = if (estado.contrasena.isNotEmpty() && !contrasenaValida) R.string.error_contrasena else null,
            botonHabilitado = correoValido && contrasenaValida
        )
    }

    private fun login() {
        val estado = _uiState.value
        if (UsuarioRepositorio.comprobarCredenciales(estado.correo.trim(), estado.contrasena)) {
            _uiState.update { it.copy(loginCorrecto = true) }
        } else {
            _uiState.update { it.copy(mensajeToast = R.string.error_credenciales) }
        }
    }
}
