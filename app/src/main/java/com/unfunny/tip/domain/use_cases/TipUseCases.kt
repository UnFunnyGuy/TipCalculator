package com.unfunny.tip.domain.use_cases

data class TipUseCases(
    val getPercentage: GetPercentage,
    val savePercentage: SavePercentage,
    val getTotal: GetTotal,
)
