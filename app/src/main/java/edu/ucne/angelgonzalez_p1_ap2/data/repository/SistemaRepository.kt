package edu.ucne.angelgonzalez_p1_ap2.data.repository

import edu.ucne.angelgonzalez_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.SistemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaDao: SistemaDao
) {
    suspend fun save(sistemaEntity: SistemaEntity) = sistemaDao.save(sistemaEntity)

    suspend fun find(id: Int): SistemaEntity? = sistemaDao.find(id)

    fun getAll(): Flow<List<SistemaEntity>> = sistemaDao.getAll()

    suspend fun delete(sistemaEntity: SistemaEntity) = sistemaDao.delete(sistemaEntity)
}