package com.humanerrors.eagleseye.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.humanerrors.eagleseye.backend.db.SavedItemDao
import com.humanerrors.eagleseye.backend.models.SavedItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SavedItemViewModel(
    private val dao: SavedItemDao
): ViewModel() {

    fun addSavedItem(savedItem: SavedItem) {
        viewModelScope.launch {
            dao.upsertSavedItem(savedItem)
        }
    }

    fun getAllSavedItems(): Flow<List<SavedItem>> {
        return dao.getAllRecords()
    }

    fun deleteSavedItem(savedItem: SavedItem) {
        viewModelScope.launch {
            dao.deleteRecord(savedItem.id)
        }
    }
}