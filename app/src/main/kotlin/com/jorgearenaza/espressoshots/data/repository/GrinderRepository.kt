package com.jorgearenaza.espressoshots.data.repository

import com.jorgearenaza.espressoshots.data.db.dao.GrinderDao
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GrinderRepository @Inject constructor(
    private val grinderDao: GrinderDao
) {
    fun getAllActiveGrinders(): Flow<List<GrinderEntity>> = grinderDao.getAllActiveGrinders()

    fun getAllGrinders(): Flow<List<GrinderEntity>> = grinderDao.getAllGrinders()

    suspend fun getGrinderById(id: Long): GrinderEntity? = grinderDao.getGrinderById(id)

    suspend fun insertGrinder(grinder: GrinderEntity): Long = grinderDao.insert(grinder)

    suspend fun updateGrinder(grinder: GrinderEntity) = grinderDao.update(grinder)

    suspend fun deleteGrinder(id: Long) = grinderDao.deleteGrinder(id)

    suspend fun deactivateGrinder(id: Long) = grinderDao.deactivateGrinder(id)

    fun getActiveGrindersCount(): Flow<Int> = grinderDao.getActiveGrindersCount()
}
