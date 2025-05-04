package com.humanerrors.eagleseye.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.humanerrors.eagleseye.R
import com.leinardi.android.speeddial.compose.FabWithLabel
import com.leinardi.android.speeddial.compose.SpeedDial
import com.leinardi.android.speeddial.compose.SpeedDialOverlay
import com.leinardi.android.speeddial.compose.SpeedDialState
import kotlin.math.exp
import kotlin.math.roundToInt


data class FloorNumberDial(
    val floor: Int,
    val label: String
)

data class CBuildingMapState(
    val floor: Int,
    val pinPlaceable: Boolean
)

@Composable
fun CBuildingScreen(
    state: CBuildingMapState,
    maxScale: Float = 5f,
    minScale: Float = 1f,
    onAddedPinEvent: (CBuildingMapState) -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    val pins = remember { mutableStateMapOf<Int, List<Offset>>() }

    Text(
        text = "Canisius & Gonzaga Building: Floor ${state.floor}",
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .zIndex(5F)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .onSizeChanged { containerSize = it }
            .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, pan, zoom, _ ->
                        Log.i("Click Input", "Pin placeable: ${state.pinPlaceable}")
                        // Handle zoom
                        val newScale = (scale * zoom).coerceIn(minScale, maxScale)

                        // Calculate max offset based on current scale
                        val maxX = (imageSize.width * newScale - containerSize.width).coerceAtLeast(0f) / 2
                        val maxY = (imageSize.height * newScale - containerSize.height).coerceAtLeast(0f) / 2

                        val newOffset = Offset(
                            x = (offset.x + pan.x).coerceIn(-maxX, maxX),
                            y = (offset.y + pan.y).coerceIn(-maxY, maxY)
                        )

                        scale = newScale
                        offset = newOffset
                    }
                )
            }
            .pointerInput(state.pinPlaceable, scale, offset, imageSize, containerSize) {
                if (state.pinPlaceable) {
                    detectTapGestures { tapOffset ->
                        // Convert tap to image coordinates (0-1 range)
                        val imageX = (tapOffset.x - containerSize.width / 2 - offset.x) / scale + imageSize.width / 2
                        val imageY = (tapOffset.y - containerSize.height / 2 - offset.y) / scale + imageSize.height / 2

                        // Normalize to 0-1 range relative to image size
                        val normalizedPosition = Offset(
                            x = (imageX / imageSize.width).coerceIn(0f, 1f),
                            y = (imageY / imageSize.height).coerceIn(0f, 1f)
                        )

                        val currentPins = pins[state.floor] ?: emptyList()
                        pins[state.floor] = currentPins + normalizedPosition
                        onAddedPinEvent(
                            CBuildingMapState(
                                state.floor,
                                false
                            )
                        )
                    }
                }

            }
    ) {
        Image(
            painter = painterResource(
                when (state.floor) {
                    1 -> R.drawable.c_ground_floor
                    2 -> R.drawable.c_second_floor
                    3 -> R.drawable.c_third_floor
                    else -> R.drawable.c_fourth_floor
                }
            ),
            contentDescription = "Building Map of the C Building",
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .onSizeChanged { newSize ->
                    imageSize = newSize
                },
            contentScale = ContentScale.Fit
        )
    }

    // Rendering the Pins
    pins[state.floor]?.let { positions ->
        positions.forEach {
            val pinX = it.x * imageSize.width * scale + offset.x + containerSize.width / 2 - imageSize.width * scale / 2
            val pinY = it.y * imageSize.height * scale + offset.y + containerSize.height / 2 - imageSize.height * scale / 2

            Icon(
                painter = painterResource(R.drawable.baseline_push_pin_24), // Your pin drawable
                contentDescription = "Location pin",
                modifier = Modifier
                    .offset { IntOffset(pinX.roundToInt() - 35, pinY.roundToInt() - 35) }
                    .rotate(25f)
                    .size((35*scale).dp), // Adjust size as needed
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun CBuildingFloorSelectorDial(
    onButtonClick: (Int) -> Unit,
    onAddPinClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var speedDialState by rememberSaveable { mutableStateOf(SpeedDialState.Collapsed) }

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

        item {
            FabWithLabel(
                onClick = {
                    onAddPinClick()
                    speedDialState = speedDialState.toggle()
                },
                fabContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                labelContent = { Text("Add Pin")}
            ) {
                Icon(Icons.Default.Add, "Add Pin")
            }
        }
    }

}