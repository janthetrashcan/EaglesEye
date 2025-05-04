package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.backend.models.BuildingInfo
import com.humanerrors.eagleseye.components.HomeCarousel
import com.humanerrors.eagleseye.components.ItemCard
import com.humanerrors.eagleseye.nav.SubScreenConstants

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ExploreScreen(
    navController: NavController = rememberNavController()
) {
    val buildingItems = listOf(
        BuildingInfo(id = 0, title = "Bellarmine-Campion Hall", description = R.string.lorem, imageSrc = R.drawable.home_carousel_image_1),
        BuildingInfo(id = 1, title = "Canisius-Gonzaga Building", description = R.string.lorem, imageSrc = R.drawable.home_carousel_image_2),
        BuildingInfo(id = 2, title = "LRC Building", description = R.string.lorem, imageSrc = R.drawable.home_carousel_image_3),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Carousel
        Text(
            text = "Explore AdZU",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )
        HomeCarousel()

        // Map
        Text(
            text = "Salvador Campus",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    navController.navigate(route = SubScreenConstants.MAP_SCREEN_ROUTE)
                }
                .padding(20.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp),
                ),

        ) {
            Image(
                painter = painterResource(R.drawable.map_thumb),
                contentDescription = "Map Thumbnail",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }

        Column (
            modifier = Modifier
                .padding(20.dp)
        ) {
            for (buildingItem in buildingItems) {
                ItemCard(buildingInfo = buildingItem,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }
}