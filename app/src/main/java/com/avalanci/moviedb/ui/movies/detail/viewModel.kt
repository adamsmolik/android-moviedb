package com.avalanci.moviedb.ui.movies.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.fold
import com.avalanci.moviedb.data.usecase.GetMovieDetail
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.ui.common.State
import com.avalanci.moviedb.ui.movies.movieIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val getMovieDetail: GetMovieDetail
) : ViewModel() {

	// Holds our view state which the UI collects via [state]
	private val _state = MutableStateFlow(MovieDetailViewState())

	val state: StateFlow<MovieDetailViewState>
		get() = _state

	init {
		val id = requireNotNull(savedStateHandle.get<Int>(movieIdArg)) { "Movie id is null" }
		loadMovie(id)
	}

	private fun loadMovie(id: Int) = getMovieDetail(id, viewModelScope) { it.fold(::handleFailure, ::handleMovie) }

	private fun handleFailure(failure: Failure) {
		_state.value = MovieDetailViewState(
			state = State.EMPTY
		)
	}

	private fun handleMovie(movie: Pair<Movie, List<Cast>>) {
		_state.value = MovieDetailViewState(
			state = State.CONTENT,
			movie = movie.first,
			castList = movie.second
		)
	}

}
