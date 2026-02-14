package com.jorgearenaza.espressoshots.data.repository

import com.jorgearenaza.espressoshots.data.db.dao.BeanDao
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeanRepository @Inject constructor(
    private val beanDao: BeanDao
) {
    fun getAllActiveBeans(): Flow<List<BeanEntity>> = beanDao.getAllActiveBeans()

    fun getAllBeans(): Flow<List<BeanEntity>> = beanDao.getAllBeans()

    suspend fun getBeanById(id: Long): BeanEntity? = beanDao.getBeanById(id)

    suspend fun insertBean(bean: BeanEntity): Long = beanDao.insert(bean)

    suspend fun updateBean(bean: BeanEntity) = beanDao.update(bean)

    suspend fun deleteBean(id: Long) = beanDao.deleteBean(id)

    suspend fun deactivateBean(id: Long) = beanDao.deactivateBean(id)

    fun getActiveBeanCount(): Flow<Int> = beanDao.getActiveBeansCount()
}
