package com.humanerrors.eagleseye.nav

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents an item in the navigation menu.
 *
 * This data class encapsulates the information needed to display and navigate to a specific
 * destination within the application. Each `NavigationItem` includes a title for display,
 * an icon for visual representation, and a route for navigation.
 *
 * @property title The title of the navigation item, displayed to the user.
 * @property icon The icon associated with the navigation item, visually representing the destination.
 * @property route The navigation route associated with this item. This route is used by the
 *                  navigation system to navigate to the correct destination.
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
