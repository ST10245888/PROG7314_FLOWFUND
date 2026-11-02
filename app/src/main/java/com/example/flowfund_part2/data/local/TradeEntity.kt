package com.example.flowfund_part2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trades")
data class TradeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,
    val asset: String,
    val tradeType: String,
    val entryPrice: Double,
    val exitPrice: Double,
    val quantity: Double,
val tradeDate: Long,
val notes: String = ""
)