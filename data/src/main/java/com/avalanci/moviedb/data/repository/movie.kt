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
				Success(api.fetchPopularMovies().results.map { movieMapper.mapToDomain(it) })
			} catch (exception: Throwable) {
				Error(Failure.ServerError)
			}
			false -> Error(Failure.NetworkConnection)
		}
	}

}

typealias Result<V> = Either<Failure, V>
fun <V> Success(v: V): Result<V> = Either.Right(v)
fun <V> Error(f: Failure): Result<V> = Either.Left(f)
