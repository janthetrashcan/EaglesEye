package com.humanerrors.eagleseye.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.ui.theme.AppTheme

// Header with Image and Text
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Header() {
    AppTheme {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_border),
                        contentDescription = "Header Icon",
                        modifier = Modifier
                            .size(36.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "EaglesEye",
                        style = MaterialTheme.typography.headlineSmall.copy(),
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}