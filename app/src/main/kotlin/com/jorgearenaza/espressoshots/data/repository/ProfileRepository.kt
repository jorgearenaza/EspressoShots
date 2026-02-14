package com.jorgearenaza.espressoshots.data.repository

import com.jorgearenaza.espressoshots.data.db.dao.ProfileDao
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) {
    fun getAllActiveProfiles(): Flow<List<ProfileEntity>> = profileDao.getAllActiveProfiles()

    fun getAllProfiles(): Flow<List<ProfileEntity>> = profileDao.getAllProfiles()

    suspend fun getProfileById(id: Long): ProfileEntity? = profileDao.getProfileById(id)

    suspend fun insertProfile(profile: ProfileEntity): Long = profileDao.insert(profile)

    suspend fun updateProfile(profile: ProfileEntity) = profileDao.update(profile)

    suspend fun deleteProfile(id: Long) = profileDao.deleteProfile(id)

    suspend fun deactivateProfile(id: Long) = profileDao.deactivateProfile(id)

    fun getActiveProfilesCount(): Flow<Int> = profileDao.getActiveProfilesCount()
}
