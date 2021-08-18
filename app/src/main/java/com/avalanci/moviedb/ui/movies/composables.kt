package com.avalanci.moviedb.ui.movies

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avalanci.moviedb.domain.model.Movie

@Preview(showBackground = true)
@Composable
fun Movies() {
	val viewModel = viewModel(MoviesViewModel::class.java)

	val viewState = viewModel.state.collectAsState()

	MoviesContent(viewState.value.movies)
}

@Composable
fun MoviesContent(
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
		MoviesList(
			movies = movies,
			Modifier
				.padding(innerPadding)
				.padding(8.dp)
		)
	}
}

@Composable
fun MoviesList(
	movies: List<Movie>,
	modifier: Modifier = Modifier
) {
	val scrollState = rememberLazyListState()

	LazyColumn(modifier = modifier, state = scrollState) {
		items(movies) {
			MovieItem(it)
		}
	}

}

@Composable
fun MovieItem(movie: Movie) {
	Row(verticalAlignment = Alignment.CenterVertically) {
		Text(movie.title, style = MaterialTheme.typography.subtitle1)
	}
}
