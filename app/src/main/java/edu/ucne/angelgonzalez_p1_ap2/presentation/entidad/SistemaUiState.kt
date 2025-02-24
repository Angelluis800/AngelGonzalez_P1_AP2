package edu.ucne.angelgonzalez_p1_ap2.presentation.entidad

import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity

data class SistemaUiState(
    val sistemaId: Int? = null,
    val nombre: String = "",
    val precio: String = "",
    val errorMessage: String? = null,
    val sistemas: List<SistemaEntity> = emptyList()
)