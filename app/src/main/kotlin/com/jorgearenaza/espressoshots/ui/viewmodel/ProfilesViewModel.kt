package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import com.jorgearenaza.espressoshots.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profilesState = MutableStateFlow<List<ProfileEntity>>(emptyList())
    val profilesState: StateFlow<List<ProfileEntity>> = _profilesState.asStateFlow()

    private val _selectedProfile = MutableStateFlow<ProfileEntity?>(null)
    val selectedProfile: StateFlow<ProfileEntity?> = _selectedProfile.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            profileRepository.getAllActiveProfiles().collect { profiles ->
                _profilesState.value = profiles
            }
        }
    }

    fun insertProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            try {
                profileRepository.insertProfile(profile)
                loadProfiles()
            } catch (e: Exception) {
                _errorMessage.value = "Error al insertar perfil: ${e.message}"
            }
        }
    }

    fun updateProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            try {
                profileRepository.updateProfile(profile)
                loadProfiles()
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar perfil: ${e.message}"
            }
        }
    }

    fun deleteProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            try {
                profileRepository.deleteProfile(profile)
                loadProfiles()
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar perfil: ${e.message}"
            }
        }
    }

    fun selectProfile(profile: ProfileEntity?) {
        _selectedProfile.value = profile
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
