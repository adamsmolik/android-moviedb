package com.avalanci.moviedb.ui.movies

import com.avalanci.moviedb.domain.model.Movie

data class MoviesViewState(
	val movies: List<Movie> = emptyList()
)
