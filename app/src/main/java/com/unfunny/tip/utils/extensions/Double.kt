package com.unfunny.tip.utils.extensions

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

fun Double.round(): Double {
   return Math.round(this * 100) / 100.0
}