package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.data.repository.GrinderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrindersViewModel @Inject constructor(
    private val grinderRepository: GrinderRepository
) : ViewModel() {

    private val _grindersState = MutableStateFlow<List<GrinderEntity>>(emptyList())
    val grindersState: StateFlow<List<GrinderEntity>> = _grindersState.asStateFlow()

    private val _selectedGrinder = MutableStateFlow<GrinderEntity?>(null)
    val selectedGrinder: StateFlow<GrinderEntity?> = _selectedGrinder.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadGrinders()
    }

    private fun loadGrinders() {
        viewModelScope.launch {
            grinderRepository.getAllActiveGrinders().collect { grinders ->
                _grindersState.value = grinders
            }
        }
    }

    fun insertGrinder(grinder: GrinderEntity) {
        viewModelScope.launch {
            try {
                grinderRepository.insertGrinder(grinder)
                loadGrinders()
            } catch (e: Exception) {
                _errorMessage.value = "Error al insertar molino: ${e.message}"
            }
        }
    }

    fun updateGrinder(grinder: GrinderEntity) {
        viewModelScope.launch {
            try {
                grinderRepository.updateGrinder(grinder)
                loadGrinders()
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar molino: ${e.message}"
            }
        }
    }

    fun deleteGrinder(grinder: GrinderEntity) {
        viewModelScope.launch {
            try {
                grinderRepository.deleteGrinder(grinder)
                loadGrinders()
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar molino: ${e.message}"
            }
        }
    }

    fun selectGrinder(grinder: GrinderEntity?) {
        _selectedGrinder.value = grinder
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
