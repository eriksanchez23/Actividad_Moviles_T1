package com.dam.cafepunto.ui.recuperar

// Eventos que la vista de Recuperar contraseña envía al ViewModel
sealed interface RecuperarUiEvent {
    data class CorreoCambiado(val correo: String) : RecuperarUiEvent
    data object BotonEnviarPulsado : RecuperarUiEvent
    data object MensajeMostrado : RecuperarUiEvent
    data object NavegacionRealizada : RecuperarUiEvent
}
