package com.example.flowfund_part2.data.repository

import com.example.flowfund_part2.data.local.JournalEntryDao
import com.example.flowfund_part2.data.local.JournalEntryEntity

class JournalRepository(
    private val journalDao: JournalEntryDao
) {
    fun getAllEntries() = journalDao.getAllEntries()

    suspend fun getEntryById(id: Long) =
        journalDao.getEntryById(id)

    suspend fun insertEntry(
        entry: JournalEntryEntity
    ): Long = journalDao.insertEntry(entry)

    suspend fun updateEntry(
        entry: JournalEntryEntity
    ) = journalDao.updateEntry(entry)

    suspend fun deleteEntry(
        entry: JournalEntryEntity
    ) = journalDao.deleteEntry(entry)
}