package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humanerrors.eagleseye.R

@Preview(showBackground = true)
@Composable
fun ExploreScreen() {
    androidx.compose.foundation.layout.Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()

        // PLACEHOLDER 1
        Text(
            text = "Explore AdZU",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp),
                )
        )

        // PLACEHOLDER 2
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
                .padding(20.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp),
                )
        )
    }
}

// Header with Image and Text
@Preview(showBackground = true)
@Composable
fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        // SCHOOL LOGO
        Image(
            painter = painterResource(id = R.drawable.adzu_seal),
            contentDescription = "Header Icon",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 8.dp)
        )

        // HEADER TITLE
        Text(
            text = "AdZU EaglesEye",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.surface
        )
    }
}