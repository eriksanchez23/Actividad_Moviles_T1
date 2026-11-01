package com.dam.cafepunto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.databinding.FragmentoTerceroBinding

class Tercer_Fragmento : Fragment() {

    private var _enlace: FragmentoTerceroBinding? = null
    private val enlace get() = _enlace!!

    override fun onCreateView(
        inflador: LayoutInflater, contenedor: ViewGroup?,
        estadoGuardado: Bundle?
    ): View {
        _enlace = FragmentoTerceroBinding.inflate(inflador, contenedor, false)
        return enlace.root
    }

    override fun onViewCreated(vista: View, estadoGuardado: Bundle?) {
        super.onViewCreated(vista, estadoGuardado)

        // Al pulsar Regístrate, vuelve a la pantalla de inicio de sesión
        enlace.registroBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_tres_a_dos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _enlace = null
    }
}
