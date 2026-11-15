package com.dam.cafepunto.data

// Modelo: datos de un usuario
data class Usuario(
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String
)

// Modelo: guarda los usuarios en memoria (trae un usuario de prueba)
object UsuarioRepositorio {

    private val usuarios = mutableListOf(
        Usuario("Demo", "demo@cafepunto.com", "123456", "600000000")
    )

    fun existe(correo: String): Boolean =
        usuarios.any { it.correo.equals(correo, ignoreCase = true) }

    fun registrar(usuario: Usuario): Boolean {
        if (existe(usuario.correo)) return false
        usuarios.add(usuario)
        return true
    }

    fun comprobarCredenciales(correo: String, contrasena: String): Boolean =
        usuarios.any { it.correo.equals(correo, ignoreCase = true) && it.contrasena == contrasena }
}
