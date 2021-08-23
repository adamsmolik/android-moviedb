package com.avalanci.moviedb.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ListProgress(
	modifier: Modifier = Modifier,
	itemContent: @Composable() (brush: Brush) -> Unit
) {
	val scrollState = rememberScrollState()

	Column(modifier = modifier.verticalScroll(scrollState)) {
		Spacer(modifier = Modifier.height(16.dp))
		repeat(5) {
			ShimmerAnimation(itemContent)
			Spacer(modifier = Modifier.height(16.dp))
		}
	}
}
