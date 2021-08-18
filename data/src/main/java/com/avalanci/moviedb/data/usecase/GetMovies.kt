package com.avalanci.moviedb.data.usecase

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.core.interactor.UseCase
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovies @Inject constructor(
	private val movieRepository: MovieRepository
) : UseCase<List<Movie>, UseCase.None>() {
	override suspend fun run(params: None): Either<Failure, List<Movie>> {
		return movieRepository.getMovies()
	}
}
