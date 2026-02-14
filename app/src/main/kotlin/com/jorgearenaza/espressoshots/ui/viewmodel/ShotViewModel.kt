package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import com.jorgearenaza.espressoshots.data.repository.ShotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShotViewModel @Inject constructor(
    private val shotRepository: ShotRepository
) : ViewModel() {

    val allShots: StateFlow<List<ShotEntity>> = shotRepository.getAllShots()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertShot(shot: ShotEntity) {
        viewModelScope.launch {
            shotRepository.insertShot(shot)
        }
    }

    fun updateShot(shot: ShotEntity) {
        viewModelScope.launch {
            shotRepository.updateShot(shot)
        }
    }

    fun deleteShot(id: Long) {
        viewModelScope.launch {
            shotRepository.deleteShot(id)
        }
    }

    suspend fun getShotById(id: Long): ShotEntity? {
        return shotRepository.getShotById(id)
    }

    fun getShotsByBeanId(beanId: Long): StateFlow<List<ShotEntity>> {
        return shotRepository.getShotsByBeanId(beanId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }
}
