package com.humanerrors.eagleseye

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.humanerrors.eagleseye.backend.models.Info
import com.humanerrors.eagleseye.nav.BottomNavigationBar
import com.humanerrors.eagleseye.nav.Screen
import com.humanerrors.eagleseye.screens.ExploreScreen
import com.humanerrors.eagleseye.screens.SavedScreen
import com.humanerrors.eagleseye.screens.UpdatesScreen
import com.humanerrors.eagleseye.ui.theme.AppTheme
import com.humanerrors.eagleseye.components.Header
import com.humanerrors.eagleseye.nav.SubScreenConstants
import com.humanerrors.eagleseye.screens.BuildingScreen
import com.humanerrors.eagleseye.screens.CBuildingFloorSelectorDial
import com.humanerrors.eagleseye.screens.CBuildingMapState
import com.humanerrors.eagleseye.screens.CBuildingScreen
import com.humanerrors.eagleseye.screens.MapScreen
import kotlinx.coroutines.flow.map

/**
 * The main activity of the application.
 *
 * This class serves as the entry point for the app and is responsible for setting up
 * the UI using Jetpack Compose. It initializes the navigation controller, sets up the
 * bottom navigation bar, and defines the overall layout of the app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppTheme {
                // Create a NavHostController to manage navigation.
                val navController = rememberNavController()
                val currentRoute by navController.currentBackStackEntryFlow
                    .map { it.destination.route }
                    .collectAsStateWithLifecycle(initialValue = null)

                var cLobbyState by remember {
                    mutableStateOf(CBuildingMapState(
                        floor = 1,
                        pinPlaceable = false
                    ))
                }
                // Scaffold provides a basic layout structure for the app.
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (currentRoute == SubScreenConstants.C_BUILDING_ROUTE) {
                            CBuildingFloorSelectorDial(
                                onButtonClick = {
                                    cLobbyState = cLobbyState.copy(floor = it)
                                },
                                onAddPinClick = {
                                    cLobbyState = cLobbyState.copy(pinPlaceable = true)
                                }
                            )
                        } else {
                            Log.i("FAB", "Current Route: $currentRoute")
                        }
                    },
                    topBar = { Header() },
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    // Display the main screen, passing the NavHostController and padding.
                    MainScreen(
                        navController = navController,
                        cLobbyState = cLobbyState,
                        cLobbyStateUpdatedEvent = {
                            cLobbyState = it
                        },
                        modifier = Modifier.padding(innerPadding) // Apply the padding to the main screen content.
                    )
                }
            }
        }
    }
}


/**
 * The main screen of the application, responsible for setting up the navigation graph.
 *
 * This composable function defines the navigation structure of the app using a [NavHost].
 * Each destination is associated with a specific screen composable.
 *
 * In adding new screens, add a new data object on [Screen] representing a screen alongside its route.
 * Then, add a file containing a composable function with the screen content within the `screens` directory.
 * Add a `NavigationItem` within the list `navigationItems` representing the new route.
 * Finally, declare a new composable within this function and set its route and body accordingly.
 *
 * @param navController The [NavHostController] that manages the navigation within the app.
 *                      It is used to create the navigation graph and to navigate between
 *                      different screens.
 * @param modifier The [Modifier] to be applied to the [NavHost]. This allows for
 *                 customization of the layout and appearance of the navigation host.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    // Absolutely horrible, hate. hate. hate. I hate this. the scalability extends to the depths of hell,
    // The bar is on the negative, this is not even the bare minimum of code quality but the extent of my wisdom
    // is finite, and Jetpack Compose is rising from its artificial shell to tell me
    // that this code sucks 📢📢📢📢📢📢📢📢 !!!!!!!
    cLobbyState: CBuildingMapState,
    cLobbyStateUpdatedEvent: (CBuildingMapState) -> Unit,
    modifier: Modifier = Modifier
) {
    val graph = navController.createGraph(startDestination = Screen.Explore.route) {
        composable(route = Screen.Explore.route) {
            ExploreScreen(navController)
        }
        composable(route = Screen.Saved.route) {
            SavedScreen(navController)
        }
        composable(route = Screen.Updates.route) {
            UpdatesScreen(navController)
        }

        // Non Nav Bar screens
        composable(route = SubScreenConstants.MAP_SCREEN_ROUTE) {
            MapScreen(navController)
        }
        composable(route = SubScreenConstants.C_BUILDING_ROUTE) {
            CBuildingScreen(cLobbyState) {
                cLobbyStateUpdatedEvent(it)
            }
        }
        composable(route = SubScreenConstants.BUILDING_SCREEN_ROUTE) {
            BuildingScreen(buildingInfo = Info.BuildingInfo(id = 0))
        }
    }

    NavHost(
        navController = navController,
        graph = graph,
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            MainScreen(
                navController = navController,
                cLobbyState = CBuildingMapState(
                    1,
                    true
                ),
                cLobbyStateUpdatedEvent = {

                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

