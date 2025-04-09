package com.humanerrors.eagleseye.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn

/**
 * The list of items for the navigation bar, each one includes the `title` to be displayed,
 * `icon` to be displayed alongside, and the unique `route` associated with each item.
 */
val navigationItems = listOf(
    NavigationItem(
        title = "Explore",
        icon = Icons.Default.LocationOn,
        route = Screen.Explore.route
    ),
    NavigationItem(
        title = "Saved",
        icon = Icons.AutoMirrored.Filled.List, // To be changed
        route = Screen.Saved.route
    ),
    NavigationItem(
        title = "Updates",
        icon = Icons.Default.Info, // To be changed
        route = Screen.Updates.route
    ),
)