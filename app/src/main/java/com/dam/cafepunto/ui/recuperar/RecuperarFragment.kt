package com.dam.cafepunto.ui.recuperar

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
import com.dam.cafepunto.databinding.FragmentRecuperarBinding
import kotlinx.coroutines.launch

class RecuperarFragment : Fragment() {

    private var _binding: FragmentRecuperarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecuperarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecuperarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recuperarTextoCorreo.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(RecuperarUiEvent.CorreoCambiado(texto.toString()))
        }
        binding.recuperarBotonEnviar.setOnClickListener {
            viewModel.onEvent(RecuperarUiEvent.BotonEnviarPulsado)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { estado -> mostrarEstado(estado) }
            }
        }
    }

    private fun mostrarEstado(estado: RecuperarUiState) {
        binding.recuperarCampoCorreo.error = estado.errorCorreo?.let { getString(it) }
        binding.recuperarBotonEnviar.isEnabled = estado.botonHabilitado

        estado.mensajeToast?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(RecuperarUiEvent.MensajeMostrado)
        }
        if (estado.correoEnviado) {
            viewModel.onEvent(RecuperarUiEvent.NavegacionRealizada)
            findNavController().navigate(R.id.accion_recuperar_a_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
