package com.avalanci.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.avalanci.moviedb.ui.movies.Movies
import com.avalanci.moviedb.ui.movies.MoviesViewModel
import com.avalanci.moviedb.ui.movies.detail.MovieDetail
import com.avalanci.moviedb.ui.movies.detail.MovieDetailViewModel
import com.avalanci.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MovieDBTheme {
				val navController = rememberNavController()

				// A surface container using the 'background' color from the theme
				Surface(color = MaterialTheme.colors.background) {
					MovieDBNavHost(navController)
				}
			}
		}
	}
}

@Composable
fun MovieDBNavHost(navController: NavHostController) {
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
