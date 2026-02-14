package com.jorgearenaza.espressoshots.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val destination: EspressoShotsDestination,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

fun getNavigationItems(): List<NavigationItem> = listOf(
    NavigationItem(
        destination = EspressoShotsDestination.SHOTS,
        label = "Shots",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavigationItem(
        destination = EspressoShotsDestination.BEANS,
        label = "Granos",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    NavigationItem(
        destination = EspressoShotsDestination.GRINDERS,
        label = "Molinos",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    NavigationItem(
        destination = EspressoShotsDestination.PROFILES,
        label = "Perfiles",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    NavigationItem(
        destination = EspressoShotsDestination.SETTINGS,
        label = "Opciones",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
)
