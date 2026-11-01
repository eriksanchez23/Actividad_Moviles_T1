package com.dam.cafepunto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment

class ActividadPrincipal : AppCompatActivity() {
    override fun onCreate(estadoGuardado: Bundle?) {
        super.onCreate(estadoGuardado)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal)) { vista, margenes ->
            val barrasSistema = margenes.getInsets(WindowInsetsCompat.Type.systemBars())
            vista.setPadding(barrasSistema.left, barrasSistema.top, barrasSistema.right, barrasSistema.bottom)
            margenes
        }
    }

    override fun onSupportNavigateUp(): Boolean
    {
        val fragmentoNavegacion =
            supportFragmentManager.findFragmentById(R.id.fragmento_navegacion)
                    as NavHostFragment


        val controladorNavegacion = fragmentoNavegacion.navController


        return controladorNavegacion.navigateUp() ||
                super.onSupportNavigateUp()


    }

}