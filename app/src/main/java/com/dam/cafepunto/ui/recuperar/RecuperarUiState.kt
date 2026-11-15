package com.dam.cafepunto.ui.recuperar

import androidx.annotation.StringRes

// Estado de la pantalla de Recuperar contraseña
data class RecuperarUiState(
    val correo: String = "",
    @StringRes val errorCorreo: Int? = null,
    val botonHabilitado: Boolean = false
)
