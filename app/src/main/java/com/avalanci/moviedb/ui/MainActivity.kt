package com.avalanci.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.avalanci.moviedb.ui.movies.MoviesNavHost
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
					MoviesNavHost(navController)
				}
			}
		}
	}
}


