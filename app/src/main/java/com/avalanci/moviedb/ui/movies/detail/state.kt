package com.avalanci.moviedb.ui.movies.detail

import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.State

data class MovieDetailViewState(
	val state: State = State.PROGRESS,
	val movie: Movie? = null,
	val castList: List<Cast> = emptyList()
)
