package com.humanerrors.eagleseye.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.backend.models.MapHotSpot

@Composable
fun MapScreen(navController: NavController) {
    val hotspots = listOf(
        MapHotSpot(
            id = 1,
            name = "C Building",
            bounds = Rect(left = 0.46f, top = 0.5f, right = 0.6f, bottom = 0.65f),
            route = "c_building_route"
        )
    )
    ZoomableMap(
        hotspots = hotspots,
        onHotspotClicked = { hotspot ->
            if (hotspot.route.isNullOrBlank()) {
                Log.d("MapScreen", "Clicked on hotspot without a route: ${hotspot.name}")
            } else {
                navController.navigate(hotspot.route)
            }
        }
    )
}

@Composable
fun ZoomableMap(
    maxScale: Float = 10f,
    minScale: Float = 1f,
    hotspots: List<MapHotSpot> = emptyList(),
    onHotspotClicked: (MapHotSpot) -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    Text(
        text = "Salvador Campus Interactive Map",
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
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { tapOffset ->
                        // Convert tap to image coordinates
                        val imageX = (tapOffset.x - containerSize.width / 2 - offset.x) / scale + imageSize.width / 2
                        val imageY = (tapOffset.y - containerSize.height / 2 - offset.y) / scale + imageSize.height / 2

                        // Check if tap is within any hotspot
                        hotspots.forEach { hotspot ->
                            val hotspotBounds = Rect(
                                left = hotspot.bounds.left * imageSize.width,
                                top = hotspot.bounds.top * imageSize.height,
                                right = hotspot.bounds.right * imageSize.width,
                                bottom = hotspot.bounds.bottom * imageSize.height
                            )

                            if (imageX in hotspotBounds.left..hotspotBounds.right &&
                                imageY in hotspotBounds.top..hotspotBounds.bottom) {
                                onHotspotClicked(hotspot)
                            }
                        }
                    }
                )
            }
    ) {
        Image(
            painter = painterResource(R.drawable.salvador_campus_map),
            contentDescription = "Full map of the Salvador Campus",
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
}