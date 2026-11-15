package com.dam.cafepunto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dam.cafepunto.R
import com.dam.cafepunto.databinding.FragmentRegistroBinding
import com.dam.cafepunto.viewmodels.RegistroUiEvent
import com.dam.cafepunto.viewmodels.RegistroUiState
import com.dam.cafepunto.viewmodels.RegistroViewModel
import kotlinx.coroutines.launch

class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!

    // El ViewModel sobrevive a la rotación, así los datos escritos no se pierden
    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Al recrear la vista (por ejemplo al girar) se rellenan los campos con el estado guardado
        val estado = viewModel.uiState.value
        rellenarSiCambia(binding.registroTextoNombre, estado.nombre)
        rellenarSiCambia(binding.registroTextoCorreo, estado.correo)
        rellenarSiCambia(binding.registroTextoContrasena, estado.contrasena)

        // Cada vez que se añade o borra una letra se avisa al ViewModel
        binding.registroTextoNombre.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(RegistroUiEvent.NombreCambiado(texto.toString()))
        }
        binding.registroTextoCorreo.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(RegistroUiEvent.CorreoCambiado(texto.toString()))
        }
        binding.registroTextoContrasena.doOnTextChanged { texto, _, _, _ ->
            viewModel.onEvent(RegistroUiEvent.ContrasenaCambiada(texto.toString()))
        }

        binding.registroBotonRegistrate.setOnClickListener {
            findNavController().navigate(R.id.accion_registro_a_login)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { estado -> mostrarEstado(estado) }
            }
        }
    }

    private fun rellenarSiCambia(campo: EditText, valor: String) {
        if (campo.text.toString() != valor) campo.setText(valor)
    }

    private fun mostrarEstado(estado: RegistroUiState) {
        binding.registroCampoCorreo.error = estado.errorCorreo?.let { getString(it) }
        binding.registroCampoContrasena.error = estado.errorContrasena?.let { getString(it) }
        binding.registroBotonRegistrate.isEnabled = estado.botonHabilitado
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
