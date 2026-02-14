package com.jorgearenaza.espressoshots.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "espresso_shots_settings")

object SettingsKeys {
    val DEFAULT_DOSE_G = doublePreferencesKey("default_dose_g")
    val DEFAULT_RATIO = doublePreferencesKey("default_ratio")
    val DEFAULT_GRINDER_ID = longPreferencesKey("default_grinder_id")
    val DEFAULT_PROFILE_ID = longPreferencesKey("default_profile_id")
    val AUTOFILL_SHOTS = booleanPreferencesKey("autofill_shots")
}

data class EspressoSettings(
    val defaultDoseG: Double = 18.0,
    val defaultRatio: Double = 2.0,
    val defaultGrinderId: Long? = null,
    val defaultProfileId: Long? = null,
    val autofillShots: Boolean = true
)

class SettingsRepository(context: Context) {
    private val dataStore = context.dataStore

    val settingsFlow: Flow<EspressoSettings> = dataStore.data.map { preferences ->
        EspressoSettings(
            defaultDoseG = preferences[SettingsKeys.DEFAULT_DOSE_G] ?: 18.0,
            defaultRatio = preferences[SettingsKeys.DEFAULT_RATIO] ?: 2.0,
            defaultGrinderId = preferences[SettingsKeys.DEFAULT_GRINDER_ID],
            defaultProfileId = preferences[SettingsKeys.DEFAULT_PROFILE_ID],
            autofillShots = preferences[SettingsKeys.AUTOFILL_SHOTS] ?: true
        )
    }

    suspend fun setDefaultDoseG(dose: Double) {
        dataStore.edit { preferences ->
            preferences[SettingsKeys.DEFAULT_DOSE_G] = dose
        }
    }

    suspend fun setDefaultRatio(ratio: Double) {
        dataStore.edit { preferences ->
            preferences[SettingsKeys.DEFAULT_RATIO] = ratio
        }
    }

    suspend fun setDefaultGrinderId(grinderId: Long?) {
        dataStore.edit { preferences ->
            if (grinderId != null) {
                preferences[SettingsKeys.DEFAULT_GRINDER_ID] = grinderId
            } else {
                preferences.remove(SettingsKeys.DEFAULT_GRINDER_ID)
            }
        }
    }

    suspend fun setDefaultProfileId(profileId: Long?) {
        dataStore.edit { preferences ->
            if (profileId != null) {
                preferences[SettingsKeys.DEFAULT_PROFILE_ID] = profileId
            } else {
                preferences.remove(SettingsKeys.DEFAULT_PROFILE_ID)
            }
        }
    }

    suspend fun setAutofillShots(autofill: Boolean) {
        dataStore.edit { preferences ->
            preferences[SettingsKeys.AUTOFILL_SHOTS] = autofill
        }
    }

    suspend fun saveAllSettings(settings: EspressoSettings) {
        dataStore.edit { preferences ->
            preferences[SettingsKeys.DEFAULT_DOSE_G] = settings.defaultDoseG
            preferences[SettingsKeys.DEFAULT_RATIO] = settings.defaultRatio
            preferences[SettingsKeys.AUTOFILL_SHOTS] = settings.autofillShots
            if (settings.defaultGrinderId != null) {
                preferences[SettingsKeys.DEFAULT_GRINDER_ID] = settings.defaultGrinderId
            } else {
                preferences.remove(SettingsKeys.DEFAULT_GRINDER_ID)
            }
            if (settings.defaultProfileId != null) {
                preferences[SettingsKeys.DEFAULT_PROFILE_ID] = settings.defaultProfileId
            } else {
                preferences.remove(SettingsKeys.DEFAULT_PROFILE_ID)
            }
        }
    }
}
