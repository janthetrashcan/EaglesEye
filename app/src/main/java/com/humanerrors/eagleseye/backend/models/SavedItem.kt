package com.humanerrors.eagleseye.backend.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,

    @ColumnInfo(name = "place_building")
    val placeBuilding: String,
    @ColumnInfo(name = "place_room")
    val placeRoom: String?,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: String // could be a better data type if we wanna sort/filter by time
)
