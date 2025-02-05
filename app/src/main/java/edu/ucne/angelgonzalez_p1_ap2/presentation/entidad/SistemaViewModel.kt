package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angelgonzalez_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EntidadViewModel @Inject constructor(
    private val sistemaDao: SistemaDao
) : ViewModel(){
    private val _uiState = MutableStateFlow(SistemaUiState())
    val uiState = _uiState.asStateFlow()




}

fun SistemaUiState.toEntity() = SistemaEntity(
    sistemaId = this.sistemaId,
    nombre = this.nombre
)