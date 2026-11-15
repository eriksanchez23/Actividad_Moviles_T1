package com.dam.cafepunto.ui.registro

import androidx.annotation.StringRes

// Estado de la pantalla de Registro (vive en el ViewModel, así no se pierde al girar la pantalla)
data class RegistroUiState(
    val nombre: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val telefono: String = "",
    @StringRes val errorNombre: Int? = null,
    @StringRes val errorCorreo: Int? = null,
    @StringRes val errorContrasena: Int? = null,
    @StringRes val errorTelefono: Int? = null,
    val botonHabilitado: Boolean = false
)
