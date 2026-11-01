package com.dam.cafepunto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.databinding.FragmentoSegundoBinding

class Segundo_Fragmento : Fragment() {

    private var _enlace: FragmentoSegundoBinding? = null
    private val enlace get() = _enlace!!

    override fun onCreateView(
        inflador: LayoutInflater, contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        _enlace = FragmentoSegundoBinding.inflate(inflador, contenedor, false)
        return enlace.root
    }

    override fun onViewCreated(vista: View, estadoGuardado: Bundle?) {
        super.onViewCreated(vista, estadoGuardado)

        // Al pulsar Regístrate, pasa a la pantalla de registro
        enlace.sesionBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_dos_a_tres)
        }

        // Al pulsar "No recuerdo mi contraseña", pasa a la pantalla de recuperar la contraseña
        enlace.sesionBotonNoRecuerdoContrasena.setOnClickListener {
            findNavController().navigate(R.id.accion_dos_a_cuatro)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace = null
    }
}
