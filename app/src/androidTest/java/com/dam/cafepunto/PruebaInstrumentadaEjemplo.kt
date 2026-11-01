package com.dam.cafepunto

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Prueba instrumentada de ejemplo, que se ejecuta en un móvil o emulador Android.
 *
 * Ver [documentación de pruebas](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PruebaInstrumentadaEjemplo {
    @Test
    fun usarContextoApp() {
        // Contexto de la app que se está probando.
        val contextoApp = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dam.cafepunto", contextoApp.packageName)
    }
}
