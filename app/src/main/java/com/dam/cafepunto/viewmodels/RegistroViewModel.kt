package com.dam.cafepunto.viewmodels

import androidx.lifecycle.ViewModel
import com.dam.cafepunto.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onEvent(evento: RegistroUiEvent) {
        when (evento) {
            is RegistroUiEvent.NombreCambiado ->
                _uiState.update { validarCampos(it.copy(nombre = evento.nombre)) }
            is RegistroUiEvent.CorreoCambiado ->
                _uiState.update { validarCampos(it.copy(correo = evento.correo)) }
            is RegistroUiEvent.ContrasenaCambiada ->
                _uiState.update { validarCampos(it.copy(contrasena = evento.contrasena)) }
            is RegistroUiEvent.TelefonoCambiado ->
                _uiState.update { validarCampos(it.copy(telefono = evento.telefono)) }
        }
    }

    // Valida todos los campos; el botón solo se habilita si todos son correctos
    private fun validarCampos(estado: RegistroUiState): RegistroUiState {
        val nombreValido = Validador.esNombreValido(estado.nombre)
        val correoValido = Validador.esCorreoValido(estado.correo)
        val contrasenaValida = Validador.esContrasenaValida(estado.contrasena)
        val telefonoValido = Validador.esTelefonoValido(estado.telefono)
        return estado.copy(
            errorNombre = if (estado.nombre.isNotEmpty() && !nombreValido) R.string.error_nombre else null,
            errorCorreo = if (estado.correo.isNotEmpty() && !correoValido) R.string.error_correo else null,
            errorContrasena = if (estado.contrasena.isNotEmpty() && !contrasenaValida) R.string.error_contrasena else null,
            errorTelefono = if (estado.telefono.isNotEmpty() && !telefonoValido) R.string.error_telefono else null,
            botonHabilitado = nombreValido && correoValido && contrasenaValida && telefonoValido
        )
    }
}
