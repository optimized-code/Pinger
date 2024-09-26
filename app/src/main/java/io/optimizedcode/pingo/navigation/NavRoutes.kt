package io.optimizedcode.pingo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings

// Define all routes object
sealed class AppRoutes(
    route: String,
    title: String,
    icon: Any
) {
    data object HomeRoute: AppRoutes(
        route = "",
        title = "",
        icon = Icons.Default.Home
    )

    data object AddLinksRoute: AppRoutes(
        route = "",
        title = "",
        icon = Icons.Default.AddCircle
    )

    data object LogsRoute: AppRoutes(
        route = "",
        title = "",
        icon = Icons.Default.Create
    )

    data object SettingsRoute: AppRoutes(
        route = "",
        title = "",
        icon = Icons.Default.Settings
    )
}