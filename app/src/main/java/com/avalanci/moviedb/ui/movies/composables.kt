package com.avalanci.moviedb.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.ListProgress

@Preview(showBackground = true)
@Composable
fun Movies() {
	val viewModel = viewModel(MoviesViewModel::class.java)

	val viewState = viewModel.state.collectAsState().value

	MoviesContent(
		viewState.progress,
		viewState.movies
	)
}

@Composable
fun MoviesContent(
	progress: Boolean,
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
		val modifier = Modifier
			.padding(innerPadding)
			.padding(start = 16.dp, end = 16.dp)
		when (progress) {
			true -> ListProgress(
				modifier
			) { MovieProgressItem(it) }
			false ->
				MoviesList(
					movies = movies,
					modifier
				)
		}
	}
}

@Composable
fun MoviesList(
	movies: List<Movie>,
	modifier: Modifier = Modifier
) {
	val scrollState = rememberLazyListState()

	LazyColumn(modifier = modifier, state = scrollState) {
		item {
			Spacer(modifier = Modifier.height(16.dp))
		}
		items(movies) {
			MovieItem(it)
			Spacer(modifier = Modifier.height(16.dp))
		}
	}

}

@Composable
fun MovieItem(movie: Movie) {
	Card(
		shape = MaterialTheme.shapes.large,
		backgroundColor = MaterialTheme.colors.surface,
		modifier = Modifier.fillMaxWidth()
	) {
		Row() {
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
	brush: Brush
) {
	Card(
		shape = MaterialTheme.shapes.large,
		backgroundColor = MaterialTheme.colors.surface,
		modifier = Modifier.fillMaxWidth()
	) {
		Row() {
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
