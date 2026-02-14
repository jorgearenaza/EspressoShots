package com.jorgearenaza.espressoshots.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jorgearenaza.espressoshots.R
import com.jorgearenaza.espressoshots.data.db.entity.GrinderEntity
import com.jorgearenaza.espressoshots.ui.components.RedFAB
import com.jorgearenaza.espressoshots.ui.components.EmptyStateScreen
import com.jorgearenaza.espressoshots.ui.viewmodel.GrinderViewModel

@Composable
fun GrindersScreen(
    onAddGrinder: () -> Unit,
    onGrinderClick: (Int) -> Unit,
    viewModel: GrinderViewModel = hiltViewModel()
) {
    val grinders by viewModel.allGrinders.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.grinders_title)) }
            )
        },
        floatingActionButton = {
            RedFAB(
                onClick = onAddGrinder,
                contentDescription = stringResource(R.string.add)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (grinders.isEmpty()) {
                EmptyStateScreen(
                    message = stringResource(R.string.grinders_empty),
                    onAddClick = onAddGrinder
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(grinders) { grinder ->
                        GrinderCard(
                            grinder = grinder,
                            onEdit = { onGrinderClick(grinder.id.toInt()) },
                            onDelete = { viewModel.deleteGrinder(grinder.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GrinderCard(
    grinder: GrinderEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onEdit)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = grinder.name,
                    style = MaterialTheme.typography.titleMedium
                )
                if (grinder.defaultAdjustment != null && grinder.defaultAdjustment.isNotEmpty()) {
                    Text(
                        text = "Ajuste por defecto: ${grinder.defaultAdjustment}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                if (grinder.notes != null && grinder.notes.isNotEmpty()) {
                    Text(
                        text = "Notas: ${grinder.notes}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
