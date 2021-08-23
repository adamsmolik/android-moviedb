package com.avalanci.moviedb.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ListProgress(
	modifier: Modifier = Modifier,
	itemContent: @Composable() (brush: Brush) -> Unit
) {
	LazyColumn(modifier = modifier) {
		item {
			Spacer(modifier = Modifier.height(16.dp))
		}
		repeat(5) {
			item {
				ShimmerAnimation(itemContent)
				Spacer(modifier = Modifier.height(16.dp))
			}
		}
	}
}
