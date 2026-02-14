package com.jorgearenaza.espressoshots.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShotDao {
    @Insert
    suspend fun insert(shot: ShotEntity): Long

    @Update
    suspend fun update(shot: ShotEntity)

    @Delete
    suspend fun delete(shot: ShotEntity)

    @Query("SELECT * FROM shots WHERE id = :id")
    suspend fun getShotById(id: Long): ShotEntity?

    @Query("SELECT * FROM shots ORDER BY dateMs DESC")
    fun getAllShots(): Flow<List<ShotEntity>>

    @Query("SELECT * FROM shots WHERE beanId = :beanId ORDER BY dateMs DESC")
    fun getShotsByBeanId(beanId: Long): Flow<List<ShotEntity>>

    @Query("SELECT * FROM shots WHERE grinderId = :grinderId ORDER BY dateMs DESC")
    fun getShotsByGrinderId(grinderId: Long): Flow<List<ShotEntity>>

    @Query("DELETE FROM shots WHERE id = :id")
    suspend fun deleteShot(id: Long)

    @Query("SELECT COUNT(*) FROM shots")
    fun getShotsCount(): Flow<Int>
}
