package com.jorgearenaza.espressoshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgearenaza.espressoshots.data.datastore.AppSettings
import com.jorgearenaza.espressoshots.data.datastore.AppSettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsManager: AppSettingsManager
) : ViewModel() {

    val appSettings: StateFlow<AppSettings> = settingsManager.appSettingsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppSettings()
        )

    fun updateDefaultDose(dose: Double) {
        viewModelScope.launch {
            settingsManager.updateDefaultDose(dose)
        }
    }

    fun updateDefaultRatio(ratio: Double) {
        viewModelScope.launch {
            settingsManager.updateDefaultRatio(ratio)
        }
    }

    fun updateDefaultYield(yield: Double) {
        viewModelScope.launch {
            settingsManager.updateDefaultYield(yield)
        }
    }

    fun updateDefaultGrinderId(grinderId: Long?) {
        viewModelScope.launch {
            settingsManager.updateDefaultGrinderId(grinderId)
        }
    }

    fun updateDefaultProfileId(profileId: Long?) {
        viewModelScope.launch {
            settingsManager.updateDefaultProfileId(profileId)
        }
    }

    fun updateAutofillShots(autofill: Boolean) {
        viewModelScope.launch {
            settingsManager.updateAutofillShots(autofill)
        }
    }
}
