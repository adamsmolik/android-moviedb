package com.avalanci.moviedb.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ListProgress(
	innerPadding: PaddingValues,
	itemContent: @Composable() (brush: Brush) -> Unit
) {
	Column(
		modifier = Modifier
			.padding(innerPadding)
			.verticalScroll(rememberScrollState())
	) {
		Spacer(modifier = Modifier.height(8.dp))
		repeat(5) {
			ShimmerAnimation(itemContent)
		}
		Spacer(modifier = Modifier.height(8.dp))
	}
}
