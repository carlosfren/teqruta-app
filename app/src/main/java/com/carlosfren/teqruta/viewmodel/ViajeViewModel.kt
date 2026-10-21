package com.carlosfren.teqruta.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViajeViewModel : ViewModel() {
    var tecnico by mutableStateOf("")
        private set

    var clinica by mutableStateOf("")
        private set

    var kilometraje by mutableStateOf("")
        private set

    var peaje by mutableStateOf("")
        private set

    // Mensajes de error para las validaciones solicitadas en la pauta
    var errorTecnico by mutableStateOf<String?>(null)
        private set

    var errorClinica by mutableStateOf<String?>(null)
        private set

    var errorKilometraje by mutableStateOf<String?>(null)
        private set

    fun onTecnicoChanged(valor: String) {
        tecnico = valor
        errorTecnico = if (valor.isBlank()) "El nombre del técnico es obligatorio" else null
    }

    fun onClinicaChanged(valor: String) {
        clinica = valor
        errorClinica = if (valor.isBlank()) "Debe ingresar una clínica o centro de destino" else null
    }

    fun onKilometrajeChanged(valor: String) {
        kilometraje = valor
        errorKilometraje = if (valor.toIntOrNull() == null || valor.toInt() <= 0) "Ingrese un kilometraje válido" else null
    }

    fun onPeajeChanged(valor: String) {
        peaje = valor
    }

    fun validarYGuardar(): Boolean {
        onTecnicoChanged(tecnico)
        onClinicaChanged(clinica)
        onKilometrajeChanged(kilometraje)

        return errorTecnico == null && errorClinica == null && errorKilometraje == null
    }
}