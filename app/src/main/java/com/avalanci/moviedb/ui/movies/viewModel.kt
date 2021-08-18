package com.avalanci.moviedb.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.interactor.UseCase
import com.avalanci.moviedb.data.usecase.GetMovies
import com.avalanci.moviedb.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
	private val getMovies: GetMovies
) : ViewModel() {

	// Holds our view state which the UI collects via [state]
	private val _state = MutableStateFlow(HomeViewState())

	val state: StateFlow<HomeViewState>
		get() = _state

	init {
		loadMovies()
	}

	private fun loadMovies() = getMovies(UseCase.None(), viewModelScope) { it.fold(::handleFailure, ::handleMovieList) }

	private fun handleFailure(failure: Failure) {
		// TODO
	}

	private fun handleMovieList(movies: List<Movie>) {
		_state.value = HomeViewState(movies)
	}

}
