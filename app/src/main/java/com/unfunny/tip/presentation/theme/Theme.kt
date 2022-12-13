package com.unfunny.tip.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TipCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> AppDarkColorScheme
            else -> AppLightColorScheme
        }
    // StatusBar Controller
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, darkTheme) {
        systemUiController.setSystemBarsColor(
            color = colorScheme.background,
            darkIcons = !darkTheme
        )
        onDispose {}
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
