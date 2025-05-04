package com.humanerrors.eagleseye.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.ui.theme.AppTheme

@Composable
fun SavedScreen(){
    val savedItems = listOf("CS.321-A", "COGNATE2-A", "CIT.013-A", "CITM.002-A", "CIT.014-A", "CS.322-A", "CIT.012-A", "BSCS - I Long Title", "BSCS - II Long Ass Title", "BSCS - III Long Ass Title, Double Line For Sure")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(savedItems.size) { index ->
                SavedItemCard(itemText = savedItems[index])
            }
        }
    }
}

@Composable
fun SavedItemCard(itemText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                // TITLE
                Text(
                    text = itemText,
                    style = MaterialTheme.typography.titleLarge
                )
                // PLACE & TIME (Could be nice to remind students when to go to class)
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = buildAnnotatedString {
                        withStyle(style = MaterialTheme.typography.labelMedium.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.error // Place Color
                        )) {
                            append("C204, C Building") // Place Content
                        }
                        append(" | ")
                        withStyle(style = MaterialTheme.typography.labelMedium.toSpanStyle().copy(
                            color = MaterialTheme.colorScheme.primary // Time Color
                        )) {
                            append("12:30PM - 1:50PM") // Time Content
                        }
                    },
                    style = MaterialTheme.typography.labelMedium
                )
                // BODY
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp, bottom = 12.dp, end = 12.dp),
                    text = "Some body description goes here. I am only typing this to be long so that I can see how the card reacts. Should we add a word limit in the final app?",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
        }

        // ICON (Probably like a pin marker thing)
        Icon(
            painter = painterResource(id = R.drawable.baseline_push_pin_24),
            contentDescription = "Item Icon",
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.TopStart)
                .offset(y = (-15).dp, x = (-10).dp)
                .rotate(-25f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SavedScreenPreview() {
    AppTheme {
        SavedScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SavedItemCardPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(10.dp)) {
            SavedItemCard("Lorem Bruh Ipsum Dolor")
        }
    }
}