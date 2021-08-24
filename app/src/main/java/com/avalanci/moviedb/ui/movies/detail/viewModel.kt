package com.avalanci.moviedb.ui.movies.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
	private val state: SavedStateHandle
): ViewModel() {

	init {
		state.get<Int>("id")
	}

}
