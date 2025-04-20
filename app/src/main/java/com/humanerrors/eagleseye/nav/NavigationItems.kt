package com.humanerrors.eagleseye.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.humanerrors.eagleseye.R


/**
 * Provides a list of [NavigationItem] objects representing the items in the bottom navigation bar.
 *
 * This composable function defines the structure and content of the bottom navigation bar by
 * creating a list of [NavigationItem] objects. Each [NavigationItem] represents a destination
 * in the app, including its title, icon, iconSelected, and associated route.
 *
 * @return A list of [NavigationItem] objects, each representing a navigation destination.
 */
@Composable
fun navigationItems() = listOf(
    NavigationItem(
        title = "Explore",
        icon = Icons.Outlined.LocationOn,
        iconSelected = Icons.Filled.LocationOn,
        route = Screen.Explore.route
    ),
    NavigationItem(
        title = "Saved",
        icon = ImageVector.vectorResource(R.drawable.bookmark_outline),
        iconSelected = ImageVector.vectorResource(R.drawable.bookmark),
        route = Screen.Saved.route
    ),
    NavigationItem(
        title = "Updates",
        icon = ImageVector.vectorResource(R.drawable.bell_outline),
        iconSelected = ImageVector.vectorResource(R.drawable.bell),
        route = Screen.Updates.route
    ),
)
