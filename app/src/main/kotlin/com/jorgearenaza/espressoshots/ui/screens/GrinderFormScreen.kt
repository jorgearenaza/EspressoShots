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
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.ui.components.RedButton
import com.jorgearenaza.espressoshots.ui.viewmodel.GrinderViewModel

@Composable
fun GrinderFormScreen(
    grinderId: Int?,
    onGrinderSaved: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: GrinderViewModel = hiltViewModel()
) {
    val nameState = remember { mutableStateOf("") }
    val defaultAdjustmentState = remember { mutableStateOf("") }
    val notesState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (grinderId != null) "Editar Molino" else "Nuevo Molino") },
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
                value = nameState.value,
                onValueChange = { nameState.value = it },
                label = { Text(stringResource(R.string.grinder_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = defaultAdjustmentState.value,
                onValueChange = { defaultAdjustmentState.value = it },
                label = { Text(stringResource(R.string.grinder_default_adj)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("6.5, 12 clicks, etc…") }
            )

            OutlinedTextField(
                value = notesState.value,
                onValueChange = { notesState.value = it },
                label = { Text(stringResource(R.string.grinder_notes)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                minLines = 3
            )

            RedButton(
                text = stringResource(R.string.save),
                onClick = {
                    if (nameState.value.isNotEmpty()) {
                        val grinder = GrinderEntity(
                            name = nameState.value,
                            defaultAdjustment = defaultAdjustmentState.value.ifEmpty { null },
                            notes = notesState.value.ifEmpty { null }
                        )
                        viewModel.insertGrinder(grinder)
                        onGrinderSaved()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
    }
}
