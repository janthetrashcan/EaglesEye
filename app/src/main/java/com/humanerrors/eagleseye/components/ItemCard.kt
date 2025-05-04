package com.humanerrors.eagleseye.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humanerrors.eagleseye.ItemInfoActivity
import com.humanerrors.eagleseye.backend.models.Info

@Composable
fun ItemCard (
    modifier: Modifier = Modifier,
    buildingInfo: Info.BuildingInfo = Info.BuildingInfo(id = 0),
) {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        ),
        modifier = modifier,
        onClick = {
            val intent = Intent(context, ItemInfoActivity::class.java)
            intent.putExtra("info", buildingInfo)
            context.startActivity(intent)
        },
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column (
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 24.dp)
            ) {
                Text(
                    text = buildingInfo.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 8.dp),
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = context.getString(buildingInfo.description),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = (-0.4).sp,
                        lineHeight = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Image (
                painter = painterResource(buildingInfo.imageSrc),
                contentDescription = buildingInfo.title,
                modifier = Modifier
                    .weight(2f)
                    .height(140.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}