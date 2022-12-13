package com.unfunny.tip.data

import com.unfunny.tip.domain.TipRepository
import com.unfunny.tip.domain.use_cases.GetPercentage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipRepositoryImpl @Inject constructor(private val datastore: TipDatastore) : TipRepository {

    override suspend fun getTipPercentage(): Flow<Double?> {
        return datastore.tipPercentage
    }

    override suspend fun savePercentage(percentage: Double) {
        datastore.savePercentage(percentage)
    }
}
