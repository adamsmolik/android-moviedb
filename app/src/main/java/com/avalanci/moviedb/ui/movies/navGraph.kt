package com.avalanci.moviedb.ui.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.avalanci.moviedb.ui.movies.detail.MovieDetail
import com.avalanci.moviedb.ui.movies.detail.MovieDetailViewModel


const val movies = "movies"
const val movieIdArg = "movie_id"

@Composable
fun MoviesNavHost(navController: NavHostController) {
	NavHost(navController = navController, startDestination = movies) {
		composable(movies) {
			val viewModel = hiltViewModel<MoviesViewModel>()
			Movies(
				viewModel = viewModel,
				onMovieClick = {
					navigateToMovieDetail(navController, it)
				}
			)
		}

		// Movie detail
		composable(
			route = "$movies/{$movieIdArg}",
			arguments = listOf(
				navArgument(movieIdArg) {
					type = NavType.IntType
				}
			)
		) {
			val viewModel = hiltViewModel<MovieDetailViewModel>()
			MovieDetail(viewModel) {
				navController.navigateUp()
			}
		}
	}
}

private fun navigateToMovieDetail(navController: NavHostController, id: Int) {
	navController.navigate("$movies/$id")
}
