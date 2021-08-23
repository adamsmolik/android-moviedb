package com.avalanci.moviedb.ui.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.avalanci.moviedb.R
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.Empty
import com.avalanci.moviedb.ui.common.ListProgress
import com.avalanci.moviedb.ui.common.State

@Preview(showBackground = true)
@Composable
fun Movies() {
	val viewModel = viewModel(MoviesViewModel::class.java)

	val viewState = viewModel.state.collectAsState().value

	MoviesContent(
		viewState.state,
		viewState.movies
	)
}

@Composable
fun MoviesContent(
	state: State,
	movies: List<Movie>
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(text = "Movies")
				}
			)
		}
	) { innerPadding ->
		val itemModifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
		when (state) {
			State.CONTENT -> MoviesList(
				innerPadding,
				itemModifier,
				movies = movies
			)
			State.PROGRESS -> ListProgress(innerPadding = innerPadding) { MovieProgressItem(itemModifier, it) }
			State.EMPTY -> Empty(string = stringResource(R.string.no_movies))
		}
	}
}

@Composable
@SuppressLint("ModifierParameter")
fun MoviesList(
	innerPadding: PaddingValues,
	itemModifier: Modifier = Modifier,
	movies: List<Movie>
) {
	val scrollState = rememberLazyListState()
	LazyColumn(contentPadding = innerPadding, state = scrollState) {
		item {
			Spacer(modifier = Modifier.height(8.dp))
		}
		items(movies) {
			MovieItem(itemModifier, it)
		}
		item {
			Spacer(modifier = Modifier.height(8.dp))
		}
	}

}

@Composable
fun MovieItem(
	modifier: Modifier = Modifier,
	movie: Movie
) {
	Card(
		shape = MaterialTheme.shapes.large,
		backgroundColor = MaterialTheme.colors.surface,
		modifier = modifier.fillMaxWidth()
	) {
		Row {
			Image(
				painter = rememberImagePainter(data = movie.posterUrl),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.weight(2.0f)
					.aspectRatio(0.7f)
			)
			Column(
				modifier = Modifier
					.weight(3.0f)
					.padding(8.dp)
			) {
				Text(movie.title, style = MaterialTheme.typography.body1)
				Spacer(modifier = Modifier.height(4.dp))
				Text(movie.releaseDate, style = MaterialTheme.typography.caption, overflow = TextOverflow.Ellipsis)
				Spacer(modifier = Modifier.height(4.dp))
				Text(
					movie.overview,
					style = MaterialTheme.typography.caption,
					maxLines = 8,
					overflow = TextOverflow.Ellipsis
				)
			}
		}
	}

}

@Composable
fun MovieProgressItem(
	modifier: Modifier = Modifier,
	brush: Brush
) {
	Card(
		shape = MaterialTheme.shapes.large,
		backgroundColor = MaterialTheme.colors.surface,
		modifier = modifier.fillMaxWidth()
	) {
		Row {
			Spacer(
				modifier = Modifier
					.weight(2.0f)
					.aspectRatio(0.7f)
					.background(brush = brush)
			)
			Column(
				modifier = Modifier
					.weight(3.0f)
					.padding(8.dp)
			) {
				Spacer(
					modifier = Modifier
						.width(80.dp)
						.height(16.dp)
						.background(brush = brush)
				)
				Spacer(modifier = Modifier.height(4.dp))
				Spacer(
					modifier = Modifier
						.width(40.dp)
						.height(12.dp)
						.background(brush = brush)
				)
				Spacer(modifier = Modifier.height(4.dp))
				repeat(5) {
					Spacer(
						modifier = Modifier
							.fillMaxWidth()
							.height(12.dp)
							.background(brush = brush)
					)
					Spacer(modifier = Modifier.height(2.dp))
				}
			}
		}
	}
}
