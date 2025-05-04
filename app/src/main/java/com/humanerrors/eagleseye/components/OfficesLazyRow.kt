package com.humanerrors.eagleseye.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humanerrors.eagleseye.ItemInfoActivity
import com.humanerrors.eagleseye.backend.models.Info
import com.humanerrors.eagleseye.backend.models.buildingItems

@Composable
fun OfficesLazyRow(
    modifier: Modifier = Modifier,
    buildingInfo: Info.BuildingInfo = Info.BuildingInfo(id = 0)
) {
    val context = LocalContext.current

    LazyRow(modifier = modifier
    ) {

        items(buildingInfo.offices) { office ->
            Card (
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(end = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface // Replace with your desired color
                ),

                onClick = {
                    val intent = Intent(context, ItemInfoActivity::class.java)
                    intent.putExtra("info", office)
                    context.startActivity(intent)
                },
            ) {
                Column (modifier = Modifier
                    .fillMaxSize()) {

                    Image (
                        painter = painterResource(office.imageSrc),
                        contentDescription = office.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    val lineHeight = 16.sp
                    val density = LocalDensity.current
                    val heightInDp = with(density) { (lineHeight.toDp() * 2) }

                    Box (modifier = Modifier
                        .height(heightInDp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = office.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                letterSpacing = (-0.4).sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}