package com.dam.cafepunto.viewmodels

// Eventos que la vista de Login envía al ViewModel
sealed interface LoginUiEvent {
    data class CorreoCambiado(val correo: String) : LoginUiEvent
    data class ContrasenaCambiada(val contrasena: String) : LoginUiEvent
    data object BotonLoginPulsado : LoginUiEvent
    data object MensajeMostrado : LoginUiEvent
    data object NavegacionRealizada : LoginUiEvent
}
