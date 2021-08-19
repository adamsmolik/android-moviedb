package com.avalanci.moviedb.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = LightBlue100,
	primaryVariant = LightBlue700,
	secondary = Amber500,
	secondaryVariant = Amber700,
	error = Red500,
	onError = Color.Black
)

private val LightColorPalette = lightColors(
	primary = LightBlue500,
	onPrimary = Color.Black,
	primaryVariant = LightBlue700,
	secondary = Amber500,
	secondaryVariant = Amber700,
	error = Red500,
	onError = Color.Black

	/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MovieDBTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
	val colors = if (darkTheme) {
		DarkColorPalette
	} else {
		LightColorPalette
	}

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}