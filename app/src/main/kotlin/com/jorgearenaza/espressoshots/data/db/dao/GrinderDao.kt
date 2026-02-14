package com.jorgearenaza.espressoshots.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrinderDao {
    @Insert
    suspend fun insert(grinder: GrinderEntity): Long

    @Update
    suspend fun update(grinder: GrinderEntity)

    @Delete
    suspend fun delete(grinder: GrinderEntity)

    @Query("SELECT * FROM grinders WHERE id = :id")
    suspend fun getGrinderById(id: Long): GrinderEntity?

    @Query("SELECT * FROM grinders WHERE active = 1 ORDER BY name ASC")
    fun getAllActiveGrinders(): Flow<List<GrinderEntity>>

    @Query("SELECT * FROM grinders ORDER BY name ASC")
    fun getAllGrinders(): Flow<List<GrinderEntity>>

    @Query("UPDATE grinders SET active = 0 WHERE id = :id")
    suspend fun deactivateGrinder(id: Long)

    @Query("DELETE FROM grinders WHERE id = :id")
    suspend fun deleteGrinder(id: Long)

    @Query("SELECT COUNT(*) FROM grinders WHERE active = 1")
    fun getActiveGrindersCount(): Flow<Int>
}
