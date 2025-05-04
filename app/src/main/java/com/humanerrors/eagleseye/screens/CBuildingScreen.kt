package com.humanerrors.eagleseye.screens

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.leinardi.android.speeddial.compose.FabWithLabel
import com.leinardi.android.speeddial.compose.SpeedDial
import com.leinardi.android.speeddial.compose.SpeedDialOverlay
import com.leinardi.android.speeddial.compose.SpeedDialState
import kotlin.math.exp


data class FloorNumberDial(
    val floor: Int,
    val label: String
)

@Composable
fun CBuildingScreen(floor: Int) {

    Box(modifier = Modifier.padding(12.dp)) {
        Text("App content goes here. Current floor: $floor", modifier = Modifier.padding(16.dp))
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CBuildingFloorSelectorDial(
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var speedDialState by rememberSaveable { mutableStateOf(SpeedDialState.Collapsed) }
    val context = LocalContext.current

    val floorNumbers = listOf(
        FloorNumberDial(1, "Ground Floor"),
        FloorNumberDial(2, "2nd Floor"),
        FloorNumberDial(3, "3rd Floor"),
        FloorNumberDial(4, "4th Floor"),
    )

    SpeedDial(
        state = speedDialState,
        onFabClick = {
            speedDialState = speedDialState.toggle()
        },
        fabClosedContent = { Icon(Icons.Default.KeyboardArrowUp, "Open menu") },
        fabOpenedContent = { Icon(Icons.Default.KeyboardArrowDown, "Close menu") },
        modifier = modifier
    ) {
        floorNumbers.forEach { floorNumber ->
            item {
                FabWithLabel(
                    onClick = {
                        onButtonClick(floorNumber.floor)
                        speedDialState = speedDialState.toggle()
                    },
                    labelContent = { Text(floorNumber.label) }
                ) {
                    Text("${floorNumber.floor}")
                }
            }
        }
    }

}