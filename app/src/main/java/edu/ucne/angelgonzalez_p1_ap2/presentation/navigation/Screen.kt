package edu.ucne.angelgonzalez_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object SistemaList : Screen()

    @Serializable
    data class Sistema(val sistemaId: Int) : Screen()
}