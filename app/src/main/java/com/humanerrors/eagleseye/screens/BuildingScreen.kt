package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.backend.models.Info
import com.humanerrors.eagleseye.components.OfficesLazyRow

@Composable
fun BuildingScreen (
    navController: NavController = rememberNavController(),
    buildingInfo: Info.BuildingInfo,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image (
            painter = painterResource(buildingInfo.imageSrc),
            contentDescription = "Building Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Column (modifier = Modifier
            .padding(horizontal = 20.dp)
        ) {
            Text (
                text = buildingInfo.title,
                style = MaterialTheme.typography.headlineMedium.copy(),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
            HorizontalDivider(thickness = 2.dp)
            Text (
                text = context.getString(buildingInfo.description),
                textAlign = TextAlign.Justify,
                lineHeight = 24.sp,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 30.dp)
            )

            if (buildingInfo.offices.isNotEmpty()) {
                Text (
                    text = "Offices",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )

                OfficesLazyRow(
                    buildingInfo = buildingInfo,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp)),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(100.dp)
        )
    }
}