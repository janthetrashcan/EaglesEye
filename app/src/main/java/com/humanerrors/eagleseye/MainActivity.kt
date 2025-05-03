package com.humanerrors.eagleseye

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.humanerrors.eagleseye.nav.BottomNavigationBar
import com.humanerrors.eagleseye.nav.Screen
import com.humanerrors.eagleseye.screens.ExploreScreen
import com.humanerrors.eagleseye.screens.SavedScreen
import com.humanerrors.eagleseye.screens.UpdatesScreen
import com.humanerrors.eagleseye.ui.theme.AppTheme

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

                // Scaffold provides a basic layout structure for the app.
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { Header() },
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    // Display the main screen, passing the NavHostController and padding.
                    MainScreen(
                        navController = navController,
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
    modifier: Modifier = Modifier
) {
    val graph = navController.createGraph(startDestination = Screen.Explore.route) {
        composable(route = Screen.Explore.route) {
            ExploreScreen()
        }
        composable(route = Screen.Saved.route) {
            SavedScreen()
        }
        composable(route = Screen.Updates.route) {
            UpdatesScreen(navController)
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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

// Header with Image and Text
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Header() {
    AppTheme {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.adzu_seal),
                        contentDescription = "Header Icon",
                        modifier = Modifier
                            .size(36.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "AdZU EaglesEye",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}