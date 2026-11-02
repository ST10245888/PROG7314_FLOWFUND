
package com.example.flowfund_part2.utils

import com.example.flowfund_part2.data.local.TradeEntity

// Stores analytics results for closed trades
data class TradeAnalytics(
    val totalClosedTrades: Int,
    val winningTrades: Int,
    val losingTrades: Int,
    val totalProfitLoss: Double,
    val winRate: Double
)

// Calculates total profit or loss
fun calculateTotalProfitLoss(
    profitLossValues: List<Double>
): Double {
    return profitLossValues.sum()
}

// Calculates analytics from profit/loss values
fun calculateAnalytics(
    closedTradeProfitLoss: List<Double>
): TradeAnalytics {
    val wins = closedTradeProfitLoss.count { it > 0.0 }
    val losses = closedTradeProfitLoss.count { it < 0.0 }
    val total = closedTradeProfitLoss.size

    return TradeAnalytics(
        totalClosedTrades = total,
        winningTrades = wins,
        losingTrades = losses,
        totalProfitLoss = closedTradeProfitLoss.sum(),
        winRate = if (total == 0) {
            0.0
        } else {
            wins.toDouble() / total * 100.0
        }
    )
}

// Calculates profit/loss for an individual trade
fun calculateTradeProfitLoss(trade: TradeEntity): Double {
    return when (trade.tradeType.trim().uppercase()) {
        "BUY" -> (trade.exitPrice - trade.entryPrice) * trade.quantity
        "SELL" -> (trade.entryPrice - trade.exitPrice) * trade.quantity
        else -> 0.0
    }
}

// Calculates analytics from saved trade records
fun calculateAnalyticsFromTrades(
    trades: List<TradeEntity>
): TradeAnalytics {
    val profitLossValues = trades.map {
        calculateTradeProfitLoss(it)
    }

    return calculateAnalytics(profitLossValues)
}
