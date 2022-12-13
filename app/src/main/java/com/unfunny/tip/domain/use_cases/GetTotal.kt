package com.unfunny.tip.domain.use_cases

import com.unfunny.tip.domain.TipRepository
import com.unfunny.tip.utils.extensions.round

class GetTotal(private val repository: TipRepository) {

    operator fun invoke(billAmount: Double, tipPercentage: Double, splits: Int): Double {
        return repository.total(billAmount, tipPercentage, splits).round()
    }
}
