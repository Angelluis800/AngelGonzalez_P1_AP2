package edu.ucne.angelgonzalez_p1_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entities")
data class Entity (
    @PrimaryKey
    val entityId: Int? = null
)
