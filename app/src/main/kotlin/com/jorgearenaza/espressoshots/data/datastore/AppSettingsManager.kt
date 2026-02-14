package com.jorgearenaza.espressoshots.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettingsManager(private val context: Context) {

    companion object {
        private const val SETTINGS_NAME = "app_settings"
        
        private val DEFAULT_DOSE_KEY = doublePreferencesKey("default_dose_g")
        private val DEFAULT_RATIO_KEY = doublePreferencesKey("default_ratio")
        private val DEFAULT_YIELD_KEY = doublePreferencesKey("default_yield_g")
        private val DEFAULT_GRINDER_ID_KEY = longPreferencesKey("default_grinder_id")
        private val DEFAULT_PROFILE_ID_KEY = longPreferencesKey("default_profile_id")
        private val AUTOFILL_SHOTS_KEY = booleanPreferencesKey("autofill_shots")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_NAME)

    val appSettingsFlow: Flow<AppSettings> = context.dataStore.data.map { preferences ->
        AppSettings(
            defaultDoseG = preferences[DEFAULT_DOSE_KEY] ?: 18.0,
            defaultRatio = preferences[DEFAULT_RATIO_KEY] ?: 2.0,
            defaultYieldG = preferences[DEFAULT_YIELD_KEY] ?: 36.0,
            defaultGrinderId = preferences[DEFAULT_GRINDER_ID_KEY],
            defaultProfileId = preferences[DEFAULT_PROFILE_ID_KEY],
            autofillShots = preferences[AUTOFILL_SHOTS_KEY] ?: true
        )
    }

    suspend fun updateDefaultDose(dose: Double) {
        updatePreference(DEFAULT_DOSE_KEY, dose)
    }

    suspend fun updateDefaultRatio(ratio: Double) {
        updatePreference(DEFAULT_RATIO_KEY, ratio)
    }

    suspend fun updateDefaultYield(yield: Double) {
        updatePreference(DEFAULT_YIELD_KEY, yield)
    }

    suspend fun updateDefaultGrinderId(grinderId: Long?) {
        updatePreference(DEFAULT_GRINDER_ID_KEY, grinderId)
    }

    suspend fun updateDefaultProfileId(profileId: Long?) {
        updatePreference(DEFAULT_PROFILE_ID_KEY, profileId)
    }

    suspend fun updateAutofillShots(autofill: Boolean) {
        updatePreference(AUTOFILL_SHOTS_KEY, autofill)
    }

    private suspend inline fun <reified T> updatePreference(key: Preferences.Key<T>, value: T?) {
        context.dataStore.edit { preferences ->
            if (value != null) {
                preferences[key] = value
            } else {
                preferences.remove(key)
            }
        }
    }
}
