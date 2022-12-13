package com.unfunny.tip.domain.use_cases

import com.unfunny.tip.domain.TipRepository
import kotlinx.coroutines.flow.Flow

class GetPercentage(private val repository: TipRepository) {

    suspend operator fun invoke(): Flow<Double?> {
        return repository.getTipPercentage()
    }
}
