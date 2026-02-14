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
import com.jorgearenaza.espressoshots.data.db.entity.BeanEntity
import com.jorgearenaza.espressoshots.ui.components.RedFAB
import com.jorgearenaza.espressoshots.ui.components.EmptyStateScreen
import com.jorgearenaza.espressoshots.ui.viewmodel.BeanViewModel
import com.jorgearenaza.espressoshots.util.daysAgoFromNow
import com.jorgearenaza.espressoshots.util.toFormattedDate

@Composable
fun BeansScreen(
    onAddBean: () -> Unit,
    onBeanClick: (Int) -> Unit,
    viewModel: BeanViewModel = hiltViewModel()
) {
    val beans by viewModel.allBeans.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.beans_title)) }
            )
        },
        floatingActionButton = {
            RedFAB(
                onClick = onAddBean,
                contentDescription = stringResource(R.string.add)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (beans.isEmpty()) {
                EmptyStateScreen(
                    message = stringResource(R.string.beans_empty),
                    onAddClick = onAddBean
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(beans) { bean ->
                        BeanCard(
                            bean = bean,
                            onEdit = { onBeanClick(bean.id.toInt()) },
                            onDelete = { viewModel.deleteBean(bean.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BeanCard(
    bean: BeanEntity,
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
                    text = "${bean.roaster} - ${bean.name}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Tostado: ${bean.roastDateMs.toFormattedDate()}",
                    style = MaterialTheme.typography.bodySmall
                )
                val daysAgo = bean.roastDateMs.daysAgoFromNow()
                Text(
                    text = "Frescura: hace $daysAgo días",
                    style = MaterialTheme.typography.labelSmall
                )
                if (bean.notes != null && bean.notes.isNotEmpty()) {
                    Text(
                        text = "Notas: ${bean.notes}",
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
