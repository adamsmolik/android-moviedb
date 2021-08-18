package com.avalanci.moviedb.domain.repository

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.domain.model.Movie

interface MovieRepository {

	suspend fun getMovies(): Either<Failure, List<Movie>>

}
