package com.dam.cafepunto.viewmodels

// Funciones de validación que usan los ViewModels
object Validador {

    private val regexCorreo = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun esCorreoValido(correo: String): Boolean = regexCorreo.matches(correo.trim())

    fun esContrasenaValida(contrasena: String): Boolean = contrasena.length >= 6

    fun esNombreValido(nombre: String): Boolean = nombre.trim().length >= 3

    fun esTelefonoValido(telefono: String): Boolean = telefono.length == 9 && telefono.all { it.isDigit() }
}
