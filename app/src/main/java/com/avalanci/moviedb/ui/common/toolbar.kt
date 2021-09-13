package com.avalanci.moviedb.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedToolBar(
	title: String,
	scrollState: ScrollState,
	onBack: () -> Unit,
	backgroundColor: Color = MaterialTheme.colors.primary,
	onBackgroundColor: Color = MaterialTheme.colors.onPrimary
) {
	val scrollStateAlpha = ((scrollState.value + 0.001f) / 1000).coerceIn(0f, 1f)
	val animatedColor by animateColorAsState(targetValue = if (scrollStateAlpha > .8f) backgroundColor else Color.Transparent)
	val animatedElevation by animateDpAsState(targetValue = if (scrollStateAlpha > .85f) 4.dp else 0.dp)

	TopAppBar(
		elevation = animatedElevation,
		backgroundColor = animatedColor,
		navigationIcon = {
			IconButton(onClick = onBack) {
				Icon(
					imageVector = Icons.Default.ArrowBack, tint = onBackgroundColor,
					contentDescription = null
				)
			}
		},
		title = {
			Text(
				text = title,
				color = onBackgroundColor,
				modifier = Modifier
					.padding(16.dp)
					.alpha(((scrollState.value + 0.001f) / 1000).coerceIn(0f, 1f))
			)
		}
	)
}
