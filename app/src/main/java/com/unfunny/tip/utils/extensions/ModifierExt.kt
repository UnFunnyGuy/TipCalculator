package com.unfunny.tip.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

fun Modifier.iconBg() = composed {
    background(
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        shape = RoundedCornerShape(12.dp)
    )
 }
