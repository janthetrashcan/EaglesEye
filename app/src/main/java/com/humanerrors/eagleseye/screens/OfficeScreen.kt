package com.humanerrors.eagleseye.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.ItemInfoActivity
import com.humanerrors.eagleseye.backend.models.Info
import com.humanerrors.eagleseye.backend.models.buildingItems

@Composable
fun OfficeScreen (
    navController: NavController = rememberNavController(),
    officeInfo: Info.OfficeInfo,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val buildings = buildingItems()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image (
            painter = painterResource(officeInfo.imageSrc),
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
                text = officeInfo.title,
                style = MaterialTheme.typography.headlineMedium.copy(),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 8.dp)
            )
            Button (
                onClick = {
                    val intent = Intent(context, ItemInfoActivity::class.java)
                    intent.putExtra("info", buildings[officeInfo.buildingId])
                    context.startActivity(intent)
                },
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Building",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .height(16.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Text (
                    text = buildingItems()[officeInfo.buildingId].title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Left,
                )
            }

            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )

            Text (
                text = context.getString(officeInfo.description),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(100.dp)
            )
        }
    }
}