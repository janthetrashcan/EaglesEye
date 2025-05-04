package com.humanerrors.eagleseye.screens

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.humanerrors.eagleseye.R
import com.humanerrors.eagleseye.ui.theme.AppTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

// New data class to hold item fields
@RequiresApi(Build.VERSION_CODES.O)
data class SavedItem constructor(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var location: String,
    var time: String,
    var description: String,
    val startTime: LocalTime = parseStartTime(time),
    val endTime: LocalTime = parseEndTime(time)
)

@RequiresApi(Build.VERSION_CODES.O)
fun parseStartTime(timeRange: String): LocalTime {
    return runCatching {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        LocalTime.parse(timeRange.split("-")[0].trim(), formatter)
    }.getOrElse { LocalTime.of(0, 0) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseEndTime(timeRange: String): LocalTime {
    return runCatching {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        LocalTime.parse(timeRange.split("-")[1].trim(), formatter)
    }.getOrElse { LocalTime.of(0, 0) }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedScreen(navController: NavController = rememberNavController()) {
    var savedItems by remember {
        mutableStateOf(
            listOf(
                SavedItem(
                    title = "CS.321-A",
                    location = "C204, C Building",
                    time = "12:30PM - 1:50PM",
                    description = "Lecture on advanced topics"
                ),
                SavedItem(
                    title = "CIT.013-A",
                    location = "C101, C Building",
                    time = "9:00AM - 10:30AM",
                    description = "Hands-on lab work"
                ),
                SavedItem(
                    title = "CIT.014-A",
                    location = "C202, C Building",
                    time = "2:00PM - 3:30PM",
                    description = "Project discussion"
                ),
                SavedItem(
                    title = "CS.322-A",
                    location = "C205, C Building",
                    time = "12:30PM - 1:50PM",
                    description = "Lecture on advanced topics"
                ),
                SavedItem(
                    title = "CS.320-A",
                    location = "C205, C Building",
                    time = "12:30PM - 1:50PM",
                    description = "Lecture on advanced topics"
                )
            )
        )
    }

    var expandedIndex by remember { mutableStateOf(-1) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<SavedItem?>(null) }

    // Confirmation dialog
    if (showConfirmationDialog && itemToDelete != null) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to remove this item?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Remove the item from the list
                        val newSavedItems = savedItems.filter { it != itemToDelete }

                        // Check if the deleted item was the expanded one
                        if (expandedIndex == savedItems.indexOf(itemToDelete)) {
                            // Reset expandedIndex if the deleted item was expanded
                            expandedIndex = -1
                        } else if (expandedIndex > savedItems.indexOf(itemToDelete)) {
                            // If the deleted item was before the expanded one, adjust expandedIndex
                            expandedIndex -= 1
                        }

                        // Ensure expandedIndex is valid after deletion (it shouldn't be out of bounds)
                        if (expandedIndex >= newSavedItems.size) {
                            expandedIndex = -1
                        }

                        // Update the savedItems and reset the deletion state
                        savedItems = newSavedItems
                        showConfirmationDialog = false
                        itemToDelete = null
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmationDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    // Main Content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(savedItems, key = { _, item -> item.id }) { index, item ->
                SavedItemCard(
                    item = item,
                    index = index,
                    isExpanded = expandedIndex == index,
                    onExpandToggle = {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    },
                    onEdit = { newItem ->
                        savedItems = savedItems.toMutableList().also {
                            it[index] = newItem
                        }
                    },
                    onDelete = {
                        itemToDelete = item
                        showConfirmationDialog = true
                    }
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SavedItemCard(
    item: SavedItem,
    index: Int,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    onEdit: (SavedItem) -> Unit,
    onDelete: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(item.title) }
    var location by remember { mutableStateOf(item.location) }
    var description by remember { mutableStateOf(item.description) }
    var startTime by remember { mutableStateOf(item.startTime) }
    var endTime by remember { mutableStateOf(item.endTime) }

    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    val context = LocalContext.current

    val startPickerDialog = remember {
        TimePickerDialog(context, { _, h, m ->
            startTime = LocalTime.of(h, m)
        }, startTime.hour, startTime.minute, false)
    }

    val endPickerDialog = remember {
        TimePickerDialog(context, { _, h, m ->
            endTime = LocalTime.of(h, m)
        }, endTime.hour, endTime.minute, false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            .then(if (!isEditing) Modifier.clickable { onExpandToggle() } else Modifier)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(3.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Column(Modifier.padding(18.dp).fillMaxWidth()) {
                if (isEditing) {
                    OutlinedTextField(title, { title = it }, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
                } else {
                    Text(title, style = MaterialTheme.typography.titleLarge)
                }

                Spacer(Modifier.height(10.dp))

                if (isEditing) {
                    TimeRow(
                        startLabel = startTime.format(formatter),
                        endLabel = endTime.format(formatter),
                        onStartClick = { startPickerDialog.show() },
                        onEndClick = { endPickerDialog.show() }
                    )

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = location,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Location (Map)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { location = "Selected on Map (C101)" }
                    )
                } else {
                    Text(
                        buildAnnotatedString {
                            withStyle(MaterialTheme.typography.labelMedium.toSpanStyle().copy(color = MaterialTheme.colorScheme.error)) {
                                append(location)
                            }
                            append(" | ")
                            withStyle(MaterialTheme.typography.labelMedium.toSpanStyle().copy(color = MaterialTheme.colorScheme.primary)) {
                                append("${startTime.format(formatter)} - ${endTime.format(formatter)}")
                            }
                        },
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                AnimatedVisibility(
                    visible = isExpanded,
                    enter = expandVertically(tween(300)) + fadeIn(),
                    exit = shrinkVertically(tween(300)) + fadeOut()
                ) {
                    Column {
                        if (isEditing) {
                            OutlinedTextField(
                                description,
                                { description = it },
                                label = { Text("Description") },
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth()
                            )
                        } else {
                            Text(description, Modifier.padding(top = 12.dp), style = MaterialTheme.typography.bodySmall)
                        }

                        Spacer(Modifier.height(20.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {/*PLACE NAVIGATE TO MAP HERE*/},
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_map_24),
                                    contentDescription = "Map",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text("View in Map")
                            }
                        }

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Edit button with icon and text
                            Button(
                                onClick = {
                                    if (isEditing) {
                                        onEdit(
                                            SavedItem(
                                                id = item.id,
                                                title = title,
                                                location = location,
                                                time = "${startTime.format(formatter)} - ${endTime.format(formatter)}",
                                                description = description
                                            )
                                        )
                                    }
                                    isEditing = !isEditing
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_edit_24),
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(if (isEditing) "Save" else "Edit")
                            }

                            // Delete button with icon and text
                            Button(
                                onClick = onDelete,
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_24),
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text("Remove")
                            }
                        }
                    }
                }
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_push_pin_24),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .zIndex(1f)
                .align(Alignment.TopStart)
                .offset(y = (-20).dp, x = (-10).dp)
                .rotate(-25f),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun TimeRow(
    startLabel: String,
    endLabel: String,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedButton(onClick = onStartClick, modifier = Modifier.weight(1f)) {
            Text("Start Time:\n $startLabel")
        }
        OutlinedButton(onClick = onEndClick, modifier = Modifier.weight(1f)) {
            Text(" End Time:\n $endLabel")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SavedScreenPreview() {
    AppTheme {
        SavedScreen()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SavedItemCardPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(10.dp)) {
            SavedItemCard(
                item = SavedItem(
                    title = "Sample Course",
                    location = "X101, X Building",
                    time = "3:00PM - 4:00PM",
                    description = "Sample class description"
                ),
                index = 0,
                isExpanded = true,
                onExpandToggle = {},
                onEdit = {},
                onDelete = {}
            )
        }
    }
}
