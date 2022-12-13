package com.unfunny.tip.domain

import kotlinx.coroutines.flow.Flow

interface TipRepository {

    suspend fun getTipPercentage(): Flow<Double?>

    suspend fun savePercentage(percentage: Double)

    fun total(billAmount: Double, tipPercentage: Double, splits: Int): Double {
        return (billAmount + (billAmount * tipPercentage / 100)) / splits
    }

}
