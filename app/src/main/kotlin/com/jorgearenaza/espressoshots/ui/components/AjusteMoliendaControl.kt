package com.jorgearenaza.espressoshots.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jorgearenaza.espressoshots.R

/**
 * AjusteMoliendaControl: Componente reutilizable para manejar ajustes de molienda.
 *
 * Hoy: TextField + presets básicos (chips) editables.
 * Futuro: Slider + stepper + presets numéricos para fase pro.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AjusteMoliendaControl(
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.shot_grind_adj),
    presets: List<String> = listOf("Espresso", "Turbo", "Filtro", "Fino", "Medio", "Grueso")
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = value ?: "",
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("6.5, 12 clicks, espresso…") }
        )

        // Presets como chips (TODO: hacer estos editables en fase pro)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            presets.forEach { preset ->
                AssistChip(
                    onClick = { onValueChange(preset) },
                    label = { Text(preset, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }
    }
}
