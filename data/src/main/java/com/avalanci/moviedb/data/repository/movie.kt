package com.avalanci.moviedb.data.repository

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.core.functional.left
import com.avalanci.moviedb.core.functional.right
import com.avalanci.moviedb.core.platform.NetworkHandler
import com.avalanci.moviedb.data.mapper.CastMapper
import com.avalanci.moviedb.data.mapper.MovieMapper
import com.avalanci.moviedb.data.remote.MovieApi
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
	private val networkHandler: NetworkHandler,
	private val api: MovieApi,
	private val movieMapper: MovieMapper,
	private val castMapper: CastMapper
) : MovieRepository {

	override suspend fun getMovies(): Either<Failure, List<Movie>> {
		return when (networkHandler.isNetworkAvailable()) {
			true -> try {
				val configuration = api.fetchConfiguration()
				val movies = api.fetchPopularMovies().results

				right(movies.map { movieMapper.mapToDomain(Pair(configuration, it)) })
			} catch (exception: Throwable) {
				left(Failure.ServerError)
			}
			false -> left(Failure.NetworkConnection)
		}
	}

	override suspend fun getMovie(movieId: Int): Either<Failure, Movie> {
		return when (networkHandler.isNetworkAvailable()) {
			true -> try {
				val configuration = api.fetchConfiguration()
				val movie = api.fetchMovie(movieId)

				right(movieMapper.mapToDomain(Pair(configuration, movie)))
			} catch (exception: Throwable) {
				left(Failure.ServerError)
			}
			false -> left(Failure.NetworkConnection)
		}
	}

	override suspend fun getCast(movieId: Int): Either<Failure, List<Cast>> {
		return when (networkHandler.isNetworkAvailable()) {
			true -> try {
				val configuration = api.fetchConfiguration()
				val castList = api.fetchCredits(movieId).cast

				right(castList.map { castMapper.mapToDomain(Pair(configuration, it)) })
			} catch (exception: Throwable) {
				left(Failure.ServerError)
			}
			false -> left(Failure.NetworkConnection)
		}
	}

}
