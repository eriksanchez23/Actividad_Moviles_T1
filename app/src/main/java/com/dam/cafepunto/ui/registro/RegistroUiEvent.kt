package com.dam.cafepunto.ui.registro

// Eventos que la vista de Registro envía al ViewModel
sealed interface RegistroUiEvent {
    data class NombreCambiado(val nombre: String) : RegistroUiEvent
    data class CorreoCambiado(val correo: String) : RegistroUiEvent
    data class ContrasenaCambiada(val contrasena: String) : RegistroUiEvent
    data class TelefonoCambiado(val telefono: String) : RegistroUiEvent
}
