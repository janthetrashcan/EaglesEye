package com.humanerrors.eagleseye.nav

/**
 * Sealed class representing the different screens in the application.
 *
 * This class defines the possible destinations within the app's navigation graph.
 * Each screen is represented as a data object, which inherits from this sealed class.
 * The `route` property is used as a unique identifier for each screen in the
 * navigation system.
 *
 * To add a new screen to the app, create a new data object that extends this class.
 *
 * @property route The unique route associated with this screen. This is used by the
 *                  navigation system to navigate to the correct screen.
 */
sealed class Screen(val route: String) {
    data object Explore : Screen("explore_screen")
    data object Saved : Screen("saved_screen")
    data object Updates : Screen("updates_screen")
}