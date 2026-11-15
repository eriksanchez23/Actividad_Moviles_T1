package com.dam.cafepunto.viewmodels

import androidx.annotation.StringRes

// Estado de la pantalla de Login
data class LoginUiState(
    val correo: String = "",
    val contrasena: String = "",
    @StringRes val errorCorreo: Int? = null,
    @StringRes val errorContrasena: Int? = null,
    val botonHabilitado: Boolean = false,
    @StringRes val mensajeToast: Int? = null,
    val loginCorrecto: Boolean = false
)
