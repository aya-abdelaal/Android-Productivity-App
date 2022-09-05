package com.ayaabdelaal.elevate.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ayaabdelaal.elevate.R

val Cairo = FontFamily(
    Font(R.font.cairo_variablefont_wght)
)

val Signika = FontFamily(
    Font(R.font.signika_variablefont_wght)
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp
    ),
    caption = TextStyle(
        fontFamily = Signika,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)
