package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import com.jorgearenaza.espressoshots.data.preferences.EspressoSettings
import com.jorgearenaza.espressoshots.data.preferences.SettingsRepository
import com.jorgearenaza.espressoshots.data.repository.ShotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShotsViewModel @Inject constructor(
    private val shotRepository: ShotRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _shotsState = MutableStateFlow<List<ShotEntity>>(emptyList())
    val shotsState: StateFlow<List<ShotEntity>> = _shotsState.asStateFlow()

    private val _selectedShot = MutableStateFlow<ShotEntity?>(null)
    val selectedShot: StateFlow<ShotEntity?> = _selectedShot.asStateFlow()

    private val _settings = MutableStateFlow<EspressoSettings>(EspressoSettings())
    val settings: StateFlow<EspressoSettings> = _settings.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadShots()
        loadSettings()
    }

    private fun loadShots() {
        viewModelScope.launch {
            shotRepository.getAllShots().collect { shots ->
                _shotsState.value = shots
            }
        }
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingsRepository.settingsFlow.collect { settings ->
                _settings.value = settings
            }
        }
    }

    fun insertShot(shot: ShotEntity) {
        viewModelScope.launch {
            try {
                shotRepository.insertShot(shot)
                loadShots()
            } catch (e: Exception) {
                _errorMessage.value = "Error al guardar shot: ${e.message}"
            }
        }
    }

    fun updateShot(shot: ShotEntity) {
        viewModelScope.launch {
            try {
                shotRepository.updateShot(shot)
                loadShots()
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar shot: ${e.message}"
            }
        }
    }

    fun deleteShot(shot: ShotEntity) {
        viewModelScope.launch {
            try {
                shotRepository.deleteShot(shot)
                loadShots()
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar shot: ${e.message}"
            }
        }
    }

    fun selectShot(shot: ShotEntity?) {
        _selectedShot.value = shot
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
