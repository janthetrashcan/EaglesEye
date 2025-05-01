package com.humanerrors.eagleseye.backend.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.humanerrors.eagleseye.backend.models.SavedItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedItemDao {

    @Upsert
    suspend fun upsertSavedItem(savedItem: SavedItem)

    @Query("SELECT * FROM saveditem")
    fun getAllRecords(): Flow<List<SavedItem>>

    @Query("DELETE FROM saveditem WHERE id = :id")
    suspend fun deleteRecord(id: Int)
}