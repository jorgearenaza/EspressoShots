package com.jorgearenaza.espressoshots.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jorgearenaza.espressoshots.R
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import com.jorgearenaza.espressoshots.ui.components.AjusteMoliendaControl
import com.jorgearenaza.espressoshots.ui.components.RedButton
import com.jorgearenaza.espressoshots.ui.viewmodel.BeanViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.GrinderViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.ProfileViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.SettingsViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.ShotViewModel
import com.jorgearenaza.espressoshots.util.toEpochMilli
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ShotFormScreen(
    shotId: Int?,
    onShotSaved: () -> Unit,
    onBackClick: () -> Unit,
    shotViewModel: ShotViewModel = hiltViewModel(),
    beanViewModel: BeanViewModel = hiltViewModel(),
    grinderViewModel: GrinderViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val beans by beanViewModel.allBeans.collectAsState()
    val grinders by grinderViewModel.allGrinders.collectAsState()
    val profiles by profileViewModel.allProfiles.collectAsState()
    val settings by settingsViewModel.appSettings.collectAsState()

    val dateState = remember { mutableStateOf(LocalDate.now()) }
    val selectedBeanId = remember { mutableStateOf<Long?>(null) }
    val selectedGrinderId = remember { mutableStateOf<Long?>(settings.defaultGrinderId) }
    val selectedProfileId = remember { mutableStateOf<Long?>(settings.defaultProfileId) }

    val doseState = remember {
        mutableStateOf(if (settings.autofillShots) settings.defaultDoseG.toString() else "")
    }
    val yieldState = remember {
        mutableStateOf(if (settings.autofillShots) settings.defaultYieldG.toString() else "")
    }
    val ratioState = remember { mutableStateOf<String?>(null) }
    val timeState = remember { mutableStateOf("") }
    val tempState = remember { mutableStateOf("") }
    val adjustmentState = remember { mutableStateOf<String?>(null) }
    val notesState = remember { mutableStateOf("") }
    val ratingState = remember { mutableStateOf("") }

    // Recalcular ratio cuando cambian dosis o rendimiento
    fun updateRatio() {
        val dose = doseState.value.toDoubleOrNull()
        val yield = yieldState.value.toDoubleOrNull()
        if (dose != null && yield != null && dose > 0) {
            ratioState.value = (yield / dose).toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (shotId != null) "Editar Shot" else "Nuevo Shot") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.Close, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Selector de fecha
            OutlinedTextField(
                value = dateState.value.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.shot_date)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Button(
                        onClick = {
                            // TODO: Implementar DatePickerDialog nativo
                        }
                    ) {
                        Text("Seleccionar")
                    }
                }
            )

            // Selector de grano
            BeanDropdown(
                selectedBeanId = selectedBeanId.value,
                beans = beans,
                onBeanSelected = { selectedBeanId.value = it }
            )

            // Selector de molino
            GrinderDropdown(
                selectedGrinderId = selectedGrinderId.value,
                grinders = grinders,
                onGrinderSelected = { selectedGrinderId.value = it }
            )

            // Selector de perfil
            ProfileDropdown(
                selectedProfileId = selectedProfileId.value,
                profiles = profiles,
                onProfileSelected = { selectedProfileId.value = it }
            )

            // Dosis
            OutlinedTextField(
                value = doseState.value,
                onValueChange = {
                    doseState.value = it
                    updateRatio()
                },
                label = { Text(stringResource(R.string.shot_dose)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Rendimiento
            OutlinedTextField(
                value = yieldState.value,
                onValueChange = {
                    yieldState.value = it
                    updateRatio()
                },
                label = { Text(stringResource(R.string.shot_yield)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Ratio (calculado, read-only)
            OutlinedTextField(
                value = ratioState.value ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.shot_ratio)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Tiempo
            OutlinedTextField(
                value = timeState.value,
                onValueChange = { timeState.value = it },
                label = { Text(stringResource(R.string.shot_time)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Temperatura
            OutlinedTextField(
                value = tempState.value,
                onValueChange = { tempState.value = it },
                label = { Text(stringResource(R.string.shot_temp)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Ajuste de molienda
            AjusteMoliendaControl(
                value = adjustmentState.value,
                onValueChange = { adjustmentState.value = it }
            )

            // Notas
            OutlinedTextField(
                value = notesState.value,
                onValueChange = { notesState.value = it },
                label = { Text(stringResource(R.string.shot_notes)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                minLines = 3
            )

            // Rating
            OutlinedTextField(
                value = ratingState.value,
                onValueChange = { ratingState.value = it },
                label = { Text(stringResource(R.string.shot_rating)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Botón Guardar
            RedButton(
                text = stringResource(R.string.save),
                onClick = {
                    if (selectedBeanId.value != null && doseState.value.isNotEmpty() && yieldState.value.isNotEmpty()) {
                        val doseG = doseState.value.toDoubleOrNull() ?: 0.0
                        val yieldG = yieldState.value.toDoubleOrNull() ?: 0.0
                        val ratio = if (doseG > 0) yieldG / doseG else 0.0

                        val shot = ShotEntity(
                            dateMs = dateState.value.atStartOfDay()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli(),
                            beanId = selectedBeanId.value!!,
                            grinderId = selectedGrinderId.value,
                            profileId = selectedProfileId.value,
                            doseG = doseG,
                            yieldG = yieldG,
                            ratio = ratio,
                            timeSeconds = timeState.value.toIntOrNull(),
                            temperatureC = tempState.value.toDoubleOrNull(),
                            grindAdjustment = adjustmentState.value,
                            notes = notesState.value.ifEmpty { null },
                            rating = ratingState.value.toIntOrNull()
                        )
                        shotViewModel.insertShot(shot)
                        onShotSaved()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun BeanDropdown(
    selectedBeanId: Long?,
    beans: List<com.jorgearenaza.espressoshots.data.db.entity.BeanEntity>,
    onBeanSelected: (Long) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedBean = beans.find { it.id == selectedBeanId }

    Column {
        OutlinedTextField(
            value = selectedBean?.let { "${it.roaster} - ${it.name}" } ?: "Seleccionar grano",
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.shot_bean)) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = true },
            trailingIcon = {
                androidx.compose.material.icons.Icons.Default.ArrowDropDown?.let {
                    Icon(it, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            beans.forEach { bean ->
                DropdownMenuItem(
                    text = { Text("${bean.roaster} - ${bean.name}") },
                    onClick = {
                        onBeanSelected(bean.id)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun GrinderDropdown(
    selectedGrinderId: Long?,
    grinders: List<com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity>,
    onGrinderSelected: (Long?) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedGrinder = grinders.find { it.id == selectedGrinderId }

    Column {
        OutlinedTextField(
            value = selectedGrinder?.name ?: "Seleccionar molino",
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.shot_grinder)) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = true },
            trailingIcon = {
                androidx.compose.material.icons.Icons.Default.ArrowDropDown?.let {
                    Icon(it, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.settings_no_grinder)) },
                onClick = {
                    onGrinderSelected(null)
                    expanded.value = false
                }
            )
            grinders.forEach { grinder ->
                DropdownMenuItem(
                    text = { Text(grinder.name) },
                    onClick = {
                        onGrinderSelected(grinder.id)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileDropdown(
    selectedProfileId: Long?,
    profiles: List<com.jorgearenaza.espressoshots.data.db.entity.ProfileEntity>,
    onProfileSelected: (Long?) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedProfile = profiles.find { it.id == selectedProfileId }

    Column {
        OutlinedTextField(
            value = selectedProfile?.name ?: "Seleccionar perfil",
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.shot_profile)) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = true },
            trailingIcon = {
                androidx.compose.material.icons.Icons.Default.ArrowDropDown?.let {
                    Icon(it, contentDescription = null)
                }
            }
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.settings_no_profile)) },
                onClick = {
                    onProfileSelected(null)
                    expanded.value = false
                }
            )
            profiles.forEach { profile ->
                DropdownMenuItem(
                    text = { Text(profile.name) },
                    onClick = {
                        onProfileSelected(profile.id)
                        expanded.value = false
                    }
                )
            }
        }
    }
}