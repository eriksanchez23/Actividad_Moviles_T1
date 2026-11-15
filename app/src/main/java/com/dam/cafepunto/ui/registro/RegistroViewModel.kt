package com.dam.cafepunto.ui.registro

import androidx.lifecycle.ViewModel
import com.dam.cafepunto.R
import com.dam.cafepunto.data.Usuario
import com.dam.cafepunto.data.UsuarioRepositorio
import com.dam.cafepunto.data.Validador
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
            RegistroUiEvent.BotonRegistroPulsado -> registrar()
            RegistroUiEvent.MensajeMostrado -> _uiState.update { it.copy(mensajeToast = null) }
            RegistroUiEvent.NavegacionRealizada -> _uiState.update { it.copy(registroCorrecto = false) }
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

    private fun registrar() {
        val estado = _uiState.value
        val usuario = Usuario(estado.nombre.trim(), estado.correo.trim(), estado.contrasena, estado.telefono)
        if (UsuarioRepositorio.registrar(usuario)) {
            _uiState.update { it.copy(registroCorrecto = true, mensajeToast = R.string.registro_correcto) }
        } else {
            _uiState.update { it.copy(mensajeToast = R.string.error_usuario_existe) }
        }
    }
}
