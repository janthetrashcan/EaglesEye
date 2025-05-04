package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.backend.models.buildingItems
import com.humanerrors.eagleseye.components.HomeCarousel
import com.humanerrors.eagleseye.components.ItemCard
import com.humanerrors.eagleseye.nav.SubScreenConstants

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ExploreScreen(
    navController: NavController = rememberNavController()
) {
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
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        )
        Text (
            text = stringResource(R.string.explore_adzu_subtitle),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium, fontSize = 12.sp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 4.dp, bottom = 20.dp)
        )
        HomeCarousel()

        // Map
        Text(
            text = "Salvador Campus",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    navController.navigate(route = SubScreenConstants.MAP_SCREEN_ROUTE)
                }
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
//                .border(
//                    width = 2.dp,
//                    color = MaterialTheme.colorScheme.primary,
//                    shape = RoundedCornerShape(12.dp),
//                ),

        ) {
            Image(
                painter = painterResource(R.drawable.map_thumb),
                contentDescription = "Map Thumbnail",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }

        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
        )
        Column (
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 4.dp)
        ) {
            for (buildingItem in buildingItems()) {
                ItemCard(buildingInfo = buildingItem,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(100.dp)
        )
    }
}