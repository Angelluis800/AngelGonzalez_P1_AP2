package edu.ucne.angelgonzalez_p1_ap2.data.repository

import edu.ucne.angelgonzalez_p1_ap2.data.local.dao.Dao
import edu.ucne.angelgonzalez_p1_ap2.data.local.entity.Entity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val entityDao: Dao
) {
    suspend fun save(entity: Entity) = entityDao.save(entity)

    suspend fun find(id: Int): Entity? = entityDao.find(id)

    fun getAll(): Flow<List<Entity>> = entityDao.getAll()

    suspend fun delete(entity: Entity) = entityDao.delete(entity)
}