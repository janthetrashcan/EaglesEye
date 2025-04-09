package com.humanerrors.eagleseye

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
    }
}

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
            UpdatesScreen()
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