package com.example.flowfund_part2.data.repository

import com.example.flowfund_part2.data.local.TradeDao
import com.example.flowfund_part2.data.local.TradeEntity

class TradeRepository(
    private val tradeDao: TradeDao
) {
    fun getAllTrades() = tradeDao.getAllTrades()

    suspend fun getTradeById(id: Long) =
        tradeDao.getTradeById(id)

    suspend fun insertTrade(trade: TradeEntity): Long =
        tradeDao.insertTrade(trade)

    suspend fun updateTrade(trade: TradeEntity) =
        tradeDao.updateTrade(trade)

    suspend fun deleteTrade(trade: TradeEntity) =
        tradeDao.deleteTrade(trade)
}