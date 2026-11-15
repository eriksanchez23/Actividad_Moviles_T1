package com.dam.cafepunto.viewmodels

import androidx.lifecycle.ViewModel
import com.dam.cafepunto.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecuperarViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RecuperarUiState())
    val uiState: StateFlow<RecuperarUiState> = _uiState.asStateFlow()

    fun onEvent(evento: RecuperarUiEvent) {
        when (evento) {
            is RecuperarUiEvent.CorreoCambiado ->
                _uiState.update { validarCampos(it.copy(correo = evento.correo)) }
        }
    }

    // El botón solo se habilita si el correo es correcto
    private fun validarCampos(estado: RecuperarUiState): RecuperarUiState {
        val correoValido = Validador.esCorreoValido(estado.correo)
        return estado.copy(
            errorCorreo = if (estado.correo.isNotEmpty() && !correoValido) R.string.error_correo else null,
            botonHabilitado = correoValido
        )
    }
}
