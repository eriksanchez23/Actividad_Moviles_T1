package com.dam.cafepunto.viewmodels

// Modelo: usuario de prueba para poder autenticarse
object UsuarioRepositorio {

    private const val CORREO = "demo@cafepunto.com"
    private const val CONTRASENA = "123456"

    fun comprobarCredenciales(correo: String, contrasena: String): Boolean =
        correo.equals(CORREO, ignoreCase = true) && contrasena == CONTRASENA
}
