package com.avalanci.moviedb.domain.repository

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie

interface MovieRepository {

	suspend fun getMovies(): Either<Failure, List<Movie>>

	suspend fun getMovie(movieId: Int): Either<Failure, Movie>

	suspend fun getCast(movieId: Int): Either<Failure, List<Cast>>

}
