package com.ayaabdelaal.elevate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//edit later
private val DarkColorPalette = darkColors(
    primary = night3,
    primaryVariant = night1,
    secondary = morning3,
    secondaryVariant = habits1,
    background = lightGray,
    surface = lightGray,
    onPrimary = lightGray,
    onSecondary = lightGray,
    onBackground = darkGray,
    onSurface = darkGray
)

private val LightColorPalette = lightColors(
    primary = night3,
    primaryVariant = night1,
    secondary = morning3,
    secondaryVariant = habits1,
    background = lightGray,
    surface = lightGray,
    onPrimary = lightGray,
    onSecondary = lightGray,
    onBackground = darkGray,
    onSurface = darkGray

)

@Composable
fun ElevateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        lightGray
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}