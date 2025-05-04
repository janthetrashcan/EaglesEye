package com.humanerrors.eagleseye.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.backend.models.buildingItems

@ExperimentalMaterial3Api
@Composable
fun HomeCarousel(
    modifier: Modifier = Modifier,
) {
    val items = buildingItems()
    val itemWidth = 350.dp
    val carouselWidth = 412.dp
    val carouselHeight = 200.dp
    val itemSpacing = 8.dp
    val contentPadding = PaddingValues(horizontal = (carouselWidth - itemWidth) / 2)
    val carouselState = rememberCarouselState { items.size }

    HorizontalUncontainedCarousel(
        state = carouselState,
        modifier = Modifier
            .width(carouselWidth)
            .height(carouselHeight)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp)),
        itemSpacing = itemSpacing,
        itemWidth = itemWidth,
        flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(state = carouselState)
    )
    { i ->
        val item = items[i]
        Image(
            modifier = Modifier.height(carouselHeight).maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = item.imageSrc),
            contentDescription = item.title,
            contentScale = ContentScale.Crop
        )
    }
}