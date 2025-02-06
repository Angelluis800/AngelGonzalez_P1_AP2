package edu.ucne.angelgonzalez_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sistemas")
data class SistemaEntity(
    @PrimaryKey
    val sistemaId: Int? = null,
    val nombre: String = "",
    val precio: String = ""
)
