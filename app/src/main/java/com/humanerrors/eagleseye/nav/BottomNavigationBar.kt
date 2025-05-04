package com.humanerrors.eagleseye.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier = modifier
    ) {
        navigationItems().forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    if (item.title == "Updates") {
                        // Navigate to RedirectToFacebookScreen instead
                        navController.navigate(Screen.Updates.route)
                    } else {
                        selectedNavigationIndex.intValue = index
                        navController.navigate(item.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selectedNavigationIndex.intValue == index) {
                            item.iconSelected
                        } else {
                            item.icon
                        },
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        modifier = Modifier
                            .padding(top = 2.dp),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}