package com.example.flowfund_part2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalEntryDao {

    @Query(
        "SELECT * FROM journal_entries ORDER BY entryDate DESC"
    )
    fun getAllEntries(): Flow<List<JournalEntryEntity>>

    @Query(
        "SELECT * FROM journal_entries WHERE id = :entryId"
    )
    suspend fun getEntryById(
        entryId: Long
    ): JournalEntryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: JournalEntryEntity): Long

    @Update
    suspend fun updateEntry(entry: JournalEntryEntity)

    @Delete
    suspend fun deleteEntry(entry: JournalEntryEntity)

    @Query("DELETE FROM journal_entries")
    suspend fun deleteAllEntries()
}