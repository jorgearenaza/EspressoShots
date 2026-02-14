package com.jorgearenaza.espressoshots.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.jorgearenaza.espressoshots.ui.screens.ShotsScreen
import com.jorgearenaza.espressoshots.ui.screens.BeansScreen
import com.jorgearenaza.espressoshots.ui.screens.GrindersScreen
import com.jorgearenaza.espressoshots.ui.screens.ProfilesScreen
import com.jorgearenaza.espressoshots.ui.screens.SettingsScreen
import com.jorgearenaza.espressoshots.ui.viewmodel.ShotViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.BeanViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.GrinderViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.ProfileViewModel
import com.jorgearenaza.espressoshots.ui.viewmodel.SettingsViewModel

@Composable
fun EspressoShotsApp() {
    var selectedDestination by remember { mutableStateOf(EspressoShotsDestination.SHOTS) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                getNavigationItems().forEach { item ->
                    NavigationBarItem(
                        selected = selectedDestination == item.destination,
                        onClick = { selectedDestination = item.destination },
                        icon = {
                            if (selectedDestination == item.destination) {
                                androidx.compose.material3.Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = item.label
                                )
                            } else {
                                androidx.compose.material3.Icon(
                                    imageVector = item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            }
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedDestination) {
            EspressoShotsDestination.SHOTS -> {
                val viewModel: ShotViewModel = hiltViewModel()
                ShotsScreen(
                    onAddShot = { /* TODO: Add shot form */ },
                    onShotClick = { /* TODO: Edit shot form */ },
                    viewModel = viewModel
                )
            }
            EspressoShotsDestination.BEANS -> {
                val viewModel: BeanViewModel = hiltViewModel()
                BeansScreen(
                    onAddBean = { /* TODO: Add bean form */ },
                    onBeanClick = { /* TODO: Edit bean form */ },
                    viewModel = viewModel
                )
            }
            EspressoShotsDestination.GRINDERS -> {
                val viewModel: GrinderViewModel = hiltViewModel()
                GrindersScreen(
                    onAddGrinder = { /* TODO: Add grinder form */ },
                    onGrinderClick = { /* TODO: Edit grinder form */ },
                    viewModel = viewModel
                )
            }
            EspressoShotsDestination.PROFILES -> {
                val viewModel: ProfileViewModel = hiltViewModel()
                ProfilesScreen(
                    onAddProfile = { /* TODO: Add profile form */ },
                    onProfileClick = { /* TODO: Edit profile form */ },
                    viewModel = viewModel
                )
            }
            EspressoShotsDestination.SETTINGS -> {
                val viewModel: SettingsViewModel = hiltViewModel()
                SettingsScreen(
                    viewModel = viewModel
                )
            }
            else -> {
                // Otros destinos no implementados en este tab
            }
        }
    }
}
