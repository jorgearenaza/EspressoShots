package com.jorgearenaza.espressoshots.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BeanDao {
    @Insert
    suspend fun insert(bean: BeanEntity): Long

    @Update
    suspend fun update(bean: BeanEntity)

    @Delete
    suspend fun delete(bean: BeanEntity)

    @Query("SELECT * FROM beans WHERE id = :id")
    suspend fun getBeanById(id: Long): BeanEntity?

    @Query("SELECT * FROM beans WHERE active = 1 ORDER BY updatedAt DESC")
    fun getAllActiveBeans(): Flow<List<BeanEntity>>

    @Query("SELECT * FROM beans ORDER BY updatedAt DESC")
    fun getAllBeans(): Flow<List<BeanEntity>>

    @Query("UPDATE beans SET active = 0 WHERE id = :id")
    suspend fun deactivateBean(id: Long)

    @Query("DELETE FROM beans WHERE id = :id")
    suspend fun deleteBean(id: Long)

    @Query("SELECT COUNT(*) FROM beans WHERE active = 1")
    fun getActiveBeansCount(): Flow<Int>
}
