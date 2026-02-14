package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import com.jorgearenaza.espressoshots.data.repository.BeanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeanViewModel @Inject constructor(
    private val beanRepository: BeanRepository
) : ViewModel() {

    val allBeans: StateFlow<List<BeanEntity>> = beanRepository.getAllActiveBeans()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertBean(bean: BeanEntity) {
        viewModelScope.launch {
            beanRepository.insertBean(bean)
        }
    }

    fun updateBean(bean: BeanEntity) {
        viewModelScope.launch {
            beanRepository.updateBean(bean)
        }
    }

    fun deleteBean(id: Long) {
        viewModelScope.launch {
            beanRepository.deleteBean(id)
        }
    }

    suspend fun getBeanById(id: Long): BeanEntity? {
        return beanRepository.getBeanById(id)
    }
}
