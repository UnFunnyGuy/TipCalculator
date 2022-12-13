package com.unfunny.tip.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            )
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        */
    )

val Typography.fieldTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }

val Typography.fieldText: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp
        )
    }

val Typography.splitTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }

val Typography.splitTotal: TextStyle
    @Composable
    get() {
        return TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            fontSize = 42.sp
        )
    }
