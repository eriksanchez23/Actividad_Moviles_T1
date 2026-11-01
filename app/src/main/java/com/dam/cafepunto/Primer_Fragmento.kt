package com.dam.cafepunto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.databinding.FragmentoPrimeroBinding

class Primer_Fragmento : Fragment() {

    private var _enlace: FragmentoPrimeroBinding? = null
    private val enlace get() = _enlace!!

    override fun onCreateView(
        inflador: LayoutInflater, contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        _enlace = FragmentoPrimeroBinding.inflate(inflador, contenedor, false)
        return enlace.root
    }

    override fun onViewCreated(vista: View, estadoGuardado: Bundle?) {
        super.onViewCreated(vista, estadoGuardado)

        // Al pulsar Iniciar Sesión, pasa a la pantalla de inicio de sesión
        enlace.inicioBotonIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.accion_uno_a_dos)
        }

        // Al pulsar Regístrate, pasa a la pantalla de registro
        enlace.inicioBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_uno_a_tres)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace = null
    }
}
