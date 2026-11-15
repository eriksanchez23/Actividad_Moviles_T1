package com.dam.cafepunto.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.R
import com.dam.cafepunto.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cada vez que se añade o borra una letra se avisa al ViewModel
        binding.sesionTextoCorreo.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(LoginUiEvent.CorreoCambiado(texto.toString()))
        }
        binding.sesionTextoContrasena.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(LoginUiEvent.ContrasenaCambiada(texto.toString()))
        }

        binding.sesionBotonAcceder.setOnClickListener {
            viewModel.onEvent(LoginUiEvent.BotonLoginPulsado)
        }
        binding.sesionBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_login_a_registro)
        }
        binding.sesionBotonNoRecuerdoContrasena.setOnClickListener {
            findNavController().navigate(R.id.accion_login_a_recuperar)
        }

        // La vista observa el estado y se actualiza sola
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { estado -> mostrarEstado(estado) }
            }
        }
    }

    private fun mostrarEstado(estado: LoginUiState) {
        binding.sesionCampoCorreo.error = estado.errorCorreo?.let { getString(it) }
        binding.sesionCampoContrasena.error = estado.errorContrasena?.let { getString(it) }
        binding.sesionBotonAcceder.isEnabled = estado.botonHabilitado

        estado.mensajeToast?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(LoginUiEvent.MensajeMostrado)
        }
        if (estado.loginCorrecto) {
            viewModel.onEvent(LoginUiEvent.NavegacionRealizada)
            findNavController().navigate(R.id.accion_login_a_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
