package com.avalanci.moviedb.data.repository

import com.avalanci.moviedb.core.exception.Failure
import com.avalanci.moviedb.core.functional.Either
import com.avalanci.moviedb.core.platform.NetworkHandler
import com.avalanci.moviedb.data.mapper.MovieMapper
import com.avalanci.moviedb.data.remote.MovieApi
import com.avalanci.moviedb.domain.model.Movie
import com.avalanci.moviedb.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
	private val networkHandler: NetworkHandler,
	private val api: MovieApi,
	private val movieMapper: MovieMapper
) : MovieRepository {

	override suspend fun getMovies(): Result<List<Movie>> {
		return when (networkHandler.isNetworkAvailable()) {
			true -> try {
				val configuration = api.fetchConfiguration()
				val movies = api.fetchPopularMovies().results

				success(movies.map { movieMapper.mapToDomain(Pair(configuration, it)) })
			} catch (exception: Throwable) {
				error(Failure.ServerError)
			}
			false -> error(Failure.NetworkConnection)
		}
	}

}

typealias Result<V> = Either<Failure, V>

fun <V> success(v: V): Result<V> = Either.Right(v)
fun <V> error(f: Failure): Result<V> = Either.Left(f)
