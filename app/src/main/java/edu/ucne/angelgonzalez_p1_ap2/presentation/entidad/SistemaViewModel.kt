package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity
import edu.ucne.angelgonzalez_p1_ap2.data.repository.SistemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val sistemaRepository: SistemaRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(SistemaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSistemas()
    }

    fun save() {
        viewModelScope.launch {
            if (isValid()) {
                sistemaRepository.save(_uiState.value.toEntity())
            }
        }
    }

    fun onNombreChange(nombre: String) {
        _uiState.update {
            it.copy(
                nombre = nombre,
                errorMessage = if (nombre.isBlank()) "Debes rellenar el Campo Nombre"
                else null
            )
        }
    }

    fun onPrecioChange(precio: String) {
        _uiState.update {
            it.copy(
                precio = precio,
                errorMessage = when {
                    precio.isBlank() -> "El precio no puede estar vacío"
                    precio.toDoubleOrNull() == null -> "El precio debe ser un número válido" // ✅ Bloquea texto inválido
                    precio.toDouble() < 0 -> "El precio debe ser mayor a 0"
                    else -> null
                }
            )
        }
    }

    fun new() {
        _uiState.value = SistemaUiState()
    }

    fun delete() {
        viewModelScope.launch {
            sistemaRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getSistemas() {
        viewModelScope.launch {
            sistemaRepository.getAll().collect { sistemas ->
                _uiState.update {
                    it.copy(sistemas = sistemas)
                }
            }
        }
    }

    fun find(sistemaId: Int) {
        viewModelScope.launch {
            if (sistemaId > 0) {
                val sistema = sistemaRepository.find(sistemaId)
                if (sistema != null) {
                    _uiState.update {
                        it.copy(
                            sistemaId = sistema.sistemaId,
                            nombre = sistema.nombre,
                            precio = sistema.precio.toString()
                        )
                    }
                }
            }
        }
    }


    fun isValid(): Boolean {
        val nombreValid = _uiState.value.nombre.isNotBlank()
        val precioValid = _uiState.value.precio.toDoubleOrNull() != null // ✅ Evita que se guarde si no es un número

        _uiState.update {
            it.copy(
                errorMessage = when {
                    !nombreValid -> "Debes rellenar el campo Nombre"
                    !precioValid -> "El precio debe ser un número válido"
                    else -> null
                }
            )
        }
        return nombreValid && precioValid
    }


}

fun SistemaUiState.toEntity() = SistemaEntity(
    sistemaId = this.sistemaId,
    nombre = this.nombre,
    precio = this.precio.toDoubleOrNull() ?: 0.0
)