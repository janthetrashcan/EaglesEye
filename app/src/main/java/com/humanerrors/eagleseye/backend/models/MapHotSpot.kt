package com.humanerrors.eagleseye.backend.models

import androidx.compose.ui.geometry.Rect

data class MapHotSpot(
    val id: Int,
    val name: String,
    val bounds: Rect,
    val route: String? = null,
)
