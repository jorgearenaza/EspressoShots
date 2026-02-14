package com.jorgearenaza.espressoshots.data.repository

import com.jorgearenaza.espressoshots.data.db.dao.ShotDao
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShotRepository @Inject constructor(
    private val shotDao: ShotDao
) {
    fun getAllShots(): Flow<List<ShotEntity>> = shotDao.getAllShots()

    suspend fun getShotById(id: Long): ShotEntity? = shotDao.getShotById(id)

    suspend fun insertShot(shot: ShotEntity): Long = shotDao.insert(shot)

    suspend fun updateShot(shot: ShotEntity) = shotDao.update(shot)

    suspend fun deleteShot(id: Long) = shotDao.deleteShot(id)

    fun getShotsByBeanId(beanId: Long): Flow<List<ShotEntity>> = shotDao.getShotsByBeanId(beanId)

    fun getShotsByGrinderId(grinderId: Long): Flow<List<ShotEntity>> = shotDao.getShotsByGrinderId(grinderId)

    fun getShotsCount(): Flow<Int> = shotDao.getShotsCount()
}
