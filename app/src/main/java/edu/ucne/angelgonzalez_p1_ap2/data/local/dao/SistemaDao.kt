package edu.ucne.angelgonzalez_p1_ap2.data.local.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity
import kotlinx.coroutines.flow.Flow

interface SistemaDao {

    @Upsert()
    suspend fun save(sistemaEntity: SistemaEntity)

    @Query(
        """
        SELECT * 
        FROM Sistemas
        WHERE sistemaId =:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): SistemaEntity?

    @Delete
    suspend fun delete(sistemaEntity: SistemaEntity)

    @Query("SELECT * FROM Sistemas")
    fun getAll(): Flow<List<SistemaEntity>>
}