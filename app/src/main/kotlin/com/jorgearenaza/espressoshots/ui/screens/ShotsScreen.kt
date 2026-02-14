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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import com.jorgearenaza.espressoshots.data.db.entity.ShotEntity
import com.jorgearenaza.espressoshots.ui.components.RedFAB
import com.jorgearenaza.espressoshots.ui.components.EmptyStateScreen
import com.jorgearenaza.espressoshots.ui.viewmodel.ShotViewModel
import com.jorgearenaza.espressoshots.util.toFormattedDateTime

@Composable
fun ShotsScreen(
    onAddShot: () -> Unit,
    onShotClick: (Int) -> Unit,
    viewModel: ShotViewModel = hiltViewModel()
) {
    val shots by viewModel.allShots.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.shots_title)) }
            )
        },
        floatingActionButton = {
            RedFAB(
                onClick = onAddShot,
                contentDescription = stringResource(R.string.add)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (shots.isEmpty()) {
                EmptyStateScreen(
                    message = stringResource(R.string.shots_empty),
                    onAddClick = onAddShot
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(shots) { shot ->
                        ShotCard(
                            shot = shot,
                            onClick = { onShotClick(shot.id.toInt()) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShotCard(
    shot: ShotEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = shot.dateMs.toFormattedDateTime(),
                    style = MaterialTheme.typography.titleMedium
                )
                if (shot.rating != null) {
                    Text(
                        text = "⭐ ${shot.rating}/5",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    Text(
                        text = "Dosis",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "${shot.doseG}g",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column {
                    Text(
                        text = "Rendimiento",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "${shot.yieldG}g",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column {
                    Text(
                        text = "Ratio",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = String.format("%.2f", shot.ratio),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (shot.notes != null && shot.notes.isNotEmpty()) {
                Divider()
                Text(
                    text = "Notas: ${shot.notes}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
