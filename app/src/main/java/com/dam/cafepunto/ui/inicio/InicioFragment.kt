package com.dam.cafepunto.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.R
import com.dam.cafepunto.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Al pulsar Iniciar Sesión, pasa a la pantalla de inicio de sesión
        binding.inicioBotonIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.accion_inicio_a_login)
        }

        // Al pulsar Regístrate, pasa a la pantalla de registro
        binding.inicioBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_inicio_a_registro)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
