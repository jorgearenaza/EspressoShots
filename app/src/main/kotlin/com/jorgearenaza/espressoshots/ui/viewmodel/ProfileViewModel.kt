package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import com.jorgearenaza.espressoshots.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val allProfiles: StateFlow<List<ProfileEntity>> = profileRepository.getAllActiveProfiles()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            profileRepository.insertProfile(profile)
        }
    }

    fun updateProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            profileRepository.updateProfile(profile)
        }
    }

    fun deleteProfile(id: Long) {
        viewModelScope.launch {
            profileRepository.deleteProfile(id)
        }
    }

    suspend fun getProfileById(id: Long): ProfileEntity? {
        return profileRepository.getProfileById(id)
    }
}
