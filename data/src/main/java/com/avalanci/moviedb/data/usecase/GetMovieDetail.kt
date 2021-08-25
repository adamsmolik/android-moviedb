package com.avalanci.moviedb.data.usecase

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.core.functional.fold
import com.avalanci.moviedb.core.functional.left
import com.avalanci.moviedb.core.functional.right
import com.avalanci.moviedb.core.interactor.UseCase
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetail @Inject constructor(
	private val movieRepository: MovieRepository
) : UseCase<Pair<Movie, List<Cast>>, Int>() {
	override suspend fun run(params: Int): Either<Failure, Pair<Movie, List<Cast>>> {
		val movieResponse = movieRepository.getMovie(params)
		val castResponse = movieRepository.getCast(params)

		return movieResponse.fold({
			left(it)
		}, { movie ->
			castResponse.fold({
				left(it)
			}, { castList ->
				right(Pair(movie, castList))
			})
		})
	}
}
