package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.humanerrors.eagleseye.R

@Composable
fun MapScreen() {
    Image(
        painter = painterResource(R.drawable.salvador_campus_map),
        contentDescription = "Full map of the Salvador Campus",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}