package com.unfunny.tip.utils.extensions

fun String.parseInput(block: (String) -> Unit) {
    when (toDoubleOrNull()) {
        null ->  {
            if (isBlank()) block(this)
        }
        else -> block(this)
    }
}