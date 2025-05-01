package com.humanerrors.eagleseye.backend.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.humanerrors.eagleseye.backend.models.SavedItem

@Database(
    entities = [SavedItem::class], // Add entities here
    version = 1 // Increment whenever schema changes
)
abstract class EaglesEyeDatabase: RoomDatabase() {
    abstract val savedItemDao: SavedItemDao
    // Each entity should ideally have its own Data Access Object (DAO)
}