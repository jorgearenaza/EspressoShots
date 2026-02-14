package com.jorgearenaza.espressoshots.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jorgearenaza.espressoshots.R
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import com.jorgearenaza.espressoshots.ui.components.RedButton
import com.jorgearenaza.espressoshots.ui.viewmodel.BeanViewModel
import java.time.LocalDate

@Composable
fun BeanFormScreen(
    beanId: Int?,
    onBeanSaved: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: BeanViewModel = hiltViewModel()
) {
    val roasterState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val roastDateState = remember { mutableStateOf(LocalDate.now()) }
    val purchaseDateState = remember { mutableStateOf(LocalDate.now()) }
    val notesState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (beanId != null) "Editar Grano" else "Nuevo Grano") },
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
            OutlinedTextField(
                value = roasterState.value,
                onValueChange = { roasterState.value = it },
                label = { Text(stringResource(R.string.bean_roaster)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                label = { Text(stringResource(R.string.bean_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = roastDateState.value.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.bean_roast_date)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Button(onClick = {
                        // TODO: Implementar DatePickerDialog
                    }) {
                        Text("Seleccionar")
                    }
                }
            )

            OutlinedTextField(
                value = purchaseDateState.value.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.bean_purchase_date)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Button(onClick = {
                        // TODO: Implementar DatePickerDialog
                    }) {
                        Text("Seleccionar")
                    }
                }
            )

            OutlinedTextField(
                value = notesState.value,
                onValueChange = { notesState.value = it },
                label = { Text(stringResource(R.string.bean_notes)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                minLines = 3
            )

            RedButton(
                text = stringResource(R.string.save),
                onClick = {
                    if (roasterState.value.isNotEmpty() && nameState.value.isNotEmpty()) {
                        val bean = BeanEntity(
                            roaster = roasterState.value,
                            name = nameState.value,
                            roastDateMs = roastDateState.value
                                .atStartOfDay()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli(),
                            purchaseDateMs = purchaseDateState.value
                                .atStartOfDay()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli(),
                            notes = notesState.value.ifEmpty { null }
                        )
                        viewModel.insertBean(bean)
                        onBeanSaved()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
    }
}
