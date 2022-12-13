package com.unfunny.tip.presentation

data class MainState(
    val billAmount: String = "",
    val tipPercentage: String = "",
    val split: Int = 1,
    val totalAmount: Double = 0.00,
)

data class StateVsEvent(
    val value: String = "",
    val onValueChange: (String) -> Unit = {}
)
