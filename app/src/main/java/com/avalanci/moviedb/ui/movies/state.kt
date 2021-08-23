package com.avalanci.moviedb.ui.movies

import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.State

data class MoviesViewState(
	val state: State = State.PROGRESS,
	val movies: List<Movie> = emptyList()
)
