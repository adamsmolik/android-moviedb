package com.avalanci.moviedb.ui.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.avalanci.moviedb.ui.Screen
import com.avalanci.moviedb.ui.movies.detail.MovieDetail
import com.avalanci.moviedb.ui.movies.detail.MovieDetailViewModel

@Composable
fun MoviesNavHost(navController: NavHostController) {
	NavHost(navController = navController, startDestination = Screen.Movies.name) {
		composable(Screen.Movies.name) {
			val viewModel = hiltViewModel<MoviesViewModel>()
			Movies(
				viewModel = viewModel,
				onMovieClick = {
					navigateToMovieDetail(navController, it)
				}
			)
		}

		// Movie detail
		val moviesName = Screen.Movies.name
		val movieIdArg = "movie_id"
		composable(
			route = "$moviesName/{$movieIdArg}",
			arguments = listOf(
				navArgument(movieIdArg) {
					type = NavType.IntType
				}
			)
		) {
			val viewModel = hiltViewModel<MovieDetailViewModel>()
			MovieDetail(viewModel)
		}
	}
}

private fun navigateToMovieDetail(navController: NavHostController, id: Int) {
	val moviesName = Screen.Movies.name
	navController.navigate("$moviesName/$id")
}
