package com.unfunny.tip.domain.use_cases

import com.unfunny.tip.domain.TipRepository

class SavePercentage(private val repository: TipRepository) {
    suspend operator fun invoke(percentage: Double){
        repository.savePercentage(percentage)
    }
}