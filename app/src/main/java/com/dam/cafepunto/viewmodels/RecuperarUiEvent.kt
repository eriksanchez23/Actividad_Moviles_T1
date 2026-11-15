package com.dam.cafepunto.viewmodels

// Eventos que la vista de Recuperar contraseña envía al ViewModel
sealed interface RecuperarUiEvent {
    data class CorreoCambiado(val correo: String) : RecuperarUiEvent
}
