package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.data.repository.GrinderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrinderViewModel @Inject constructor(
    private val grinderRepository: GrinderRepository
) : ViewModel() {

    val allGrinders: StateFlow<List<GrinderEntity>> = grinderRepository.getAllActiveGrinders()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertGrinder(grinder: GrinderEntity) {
        viewModelScope.launch {
            grinderRepository.insertGrinder(grinder)
        }
    }

    fun updateGrinder(grinder: GrinderEntity) {
        viewModelScope.launch {
            grinderRepository.updateGrinder(grinder)
        }
    }

    fun deleteGrinder(id: Long) {
        viewModelScope.launch {
            grinderRepository.deleteGrinder(id)
        }
    }

    suspend fun getGrinderById(id: Long): GrinderEntity? {
        return grinderRepository.getGrinderById(id)
    }
}
