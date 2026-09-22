package com.example.tugas02.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PinkSoft,
    secondary = PinkSoft,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,
    secondary = PinkDark,
    tertiary = PinkSoft,
    background = PinkBackground,
    surface = Color.White
)

@Composable
fun Tugas02Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // dimatikan supaya warna pink kita tidak ditimpa warna wallpaper HP
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
