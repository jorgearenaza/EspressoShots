package com.jorgearenaza.espressoshots.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jorgearenaza.espressoshots.R
import com.jorgearenaza.espressoshots.data.datastore.AppSettings
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity
import com.jorgearenaza.espressoshots.ui.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val appSettings by viewModel.appSettings.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Estados locales para editar los ajustes antes de guardar
    var defaultDoseG by remember(appSettings) { mutableStateOf(appSettings.defaultDoseG.toString()) }
    var defaultRatio by remember(appSettings) { mutableStateOf(appSettings.defaultRatio.toString()) }
    var autofillShots by remember(appSettings) { mutableStateOf(appSettings.autofillShots) }
    var defaultGrinderId by remember(appSettings) { mutableStateOf(appSettings.defaultGrinderId) }
    var defaultProfileId by remember(appSettings) { mutableStateOf(appSettings.defaultProfileId) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            // Dosis por defecto
            TextField(
                value = defaultDoseG,
                onValueChange = { defaultDoseG = it },
                label = { Text(stringResource(R.string.settings_default_dose)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Ratio por defecto
            TextField(
                value = defaultRatio,
                onValueChange = { defaultRatio = it },
                label = { Text(stringResource(R.string.settings_default_ratio)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Autocompletar Shots
            SettingCheckbox(
                label = stringResource(R.string.settings_autofill),
                checked = autofillShots,
                onCheckedChange = { autofillShots = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón Guardar (rojo)
            Button(
                onClick = {
                    // Validar y guardar
                    val doseVal = defaultDoseG.toDoubleOrNull() ?: appSettings.defaultDoseG
                    val ratioVal = defaultRatio.toDoubleOrNull() ?: appSettings.defaultRatio

                    viewModel.updateDefaultDose(doseVal)
                    viewModel.updateDefaultRatio(ratioVal)
                    viewModel.updateAutofillShots(autofillShots)
                    if (defaultGrinderId != null) {
                        viewModel.updateDefaultGrinderId(defaultGrinderId)
                    }
                    if (defaultProfileId != null) {
                        viewModel.updateDefaultProfileId(defaultProfileId)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@Composable
fun SettingCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = label,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
