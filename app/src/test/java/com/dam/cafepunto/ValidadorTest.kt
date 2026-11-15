package com.dam.cafepunto

import com.dam.cafepunto.data.Validador
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidadorTest {

    @Test
    fun correo() {
        assertTrue(Validador.esCorreoValido("demo@cafepunto.com"))
        assertFalse(Validador.esCorreoValido("demo@"))
        assertFalse(Validador.esCorreoValido(""))
    }

    @Test
    fun contrasena() {
        assertTrue(Validador.esContrasenaValida("123456"))
        assertFalse(Validador.esContrasenaValida("123"))
    }

    @Test
    fun nombre() {
        assertTrue(Validador.esNombreValido("Ana"))
        assertFalse(Validador.esNombreValido("A "))
    }

    @Test
    fun telefono() {
        assertTrue(Validador.esTelefonoValido("600123456"))
        assertFalse(Validador.esTelefonoValido("60012"))
        assertFalse(Validador.esTelefonoValido("60012345a"))
    }
}
