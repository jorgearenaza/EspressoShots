package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import com.jorgearenaza.espressoshots.data.repository.BeanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeansViewModel @Inject constructor(
    private val beanRepository: BeanRepository
) : ViewModel() {

    private val _beansState = MutableStateFlow<List<BeanEntity>>(emptyList())
    val beansState: StateFlow<List<BeanEntity>> = _beansState.asStateFlow()

    private val _selectedBean = MutableStateFlow<BeanEntity?>(null)
    val selectedBean: StateFlow<BeanEntity?> = _selectedBean.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadBeans()
    }

    private fun loadBeans() {
        viewModelScope.launch {
            beanRepository.getAllActiveBeans().collect { beans ->
                _beansState.value = beans
            }
        }
    }

    fun insertBean(bean: BeanEntity) {
        viewModelScope.launch {
            try {
                beanRepository.insertBean(bean)
                loadBeans()
            } catch (e: Exception) {
                _errorMessage.value = "Error al insertar grano: ${e.message}"
            }
        }
    }

    fun updateBean(bean: BeanEntity) {
        viewModelScope.launch {
            try {
                beanRepository.updateBean(bean)
                loadBeans()
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar grano: ${e.message}"
            }
        }
    }

    fun deleteBean(bean: BeanEntity) {
        viewModelScope.launch {
            try {
                beanRepository.deleteBean(bean)
                loadBeans()
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar grano: ${e.message}"
            }
        }
    }

    fun selectBean(bean: BeanEntity?) {
        _selectedBean.value = bean
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
