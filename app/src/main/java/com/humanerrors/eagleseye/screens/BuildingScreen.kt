package com.humanerrors.eagleseye.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.components.BuildingInfo

@Composable
fun BuildingScreen (
    navController: NavController = rememberNavController(),
    buildingInfo: BuildingInfo
) {
    val context = LocalContext.current

    Column () {
        Image (
            painter = painterResource(buildingInfo.imageSrc),
            contentDescription = "Building Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Column (modifier = Modifier
            .padding(horizontal = 20.dp)
        ) {
            Text (
                text = buildingInfo.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
            HorizontalDivider(thickness = 2.dp)
            Text (
                text = context.getString(buildingInfo.description),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
        }
    }
}