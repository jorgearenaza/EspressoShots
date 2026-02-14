package com.jorgearenaza.espressoshots.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert
    suspend fun insert(profile: ProfileEntity): Long

    @Update
    suspend fun update(profile: ProfileEntity)

    @Delete
    suspend fun delete(profile: ProfileEntity)

    @Query("SELECT * FROM profiles WHERE id = :id")
    suspend fun getProfileById(id: Long): ProfileEntity?

    @Query("SELECT * FROM profiles WHERE active = 1 ORDER BY name ASC")
    fun getAllActiveProfiles(): Flow<List<ProfileEntity>>

    @Query("SELECT * FROM profiles ORDER BY name ASC")
    fun getAllProfiles(): Flow<List<ProfileEntity>>

    @Query("UPDATE profiles SET active = 0 WHERE id = :id")
    suspend fun deactivateProfile(id: Long)

    @Query("DELETE FROM profiles WHERE id = :id")
    suspend fun deleteProfile(id: Long)

    @Query("SELECT COUNT(*) FROM profiles WHERE active = 1")
    fun getActiveProfilesCount(): Flow<Int>
}
