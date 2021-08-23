package com.avalanci.moviedb.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avalanci.moviedb.R

enum class State {
	CONTENT, PROGRESS, EMPTY
}

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

@Composable
fun Empty(
	string: String = stringResource(R.string.empty),
	imagePainter: Painter = painterResource(R.drawable.ic_empty)
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = imagePainter,
			contentDescription = stringResource(R.string.empty),
			colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
			modifier = Modifier
				.padding(
					top = 16.dp,
					start = 16.dp,
					bottom = 8.dp,
					end = 16.dp
				)
				.size(48.dp)
		)
		Text(
			text = string,
			style = MaterialTheme.typography.body1,
			modifier = Modifier.padding(
				top = 8.dp,
				start = 16.dp,
				bottom = 16.dp,
				end = 16.dp
			)
		)
	}
}
