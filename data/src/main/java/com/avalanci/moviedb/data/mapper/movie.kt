package com.avalanci.moviedb.data.mapper

import com.avalanci.moviedb.data.entity.CastEntity
import com.avalanci.moviedb.data.entity.ConfigurationEntity
import com.avalanci.moviedb.data.entity.MovieEntity
import com.avalanci.moviedb.domain.model.Cast
import com.avalanci.moviedb.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMapper @Inject constructor() : Mapper<Movie, Pair<ConfigurationEntity, MovieEntity>> {

	override fun mapToDomain(entity: Pair<ConfigurationEntity, MovieEntity>): Movie {
		val configurationEntity = entity.first
		val movieEntity = entity.second

		return Movie(
			movieEntity.id,
			movieEntity.title,
			movieEntity.overview,
			movieEntity.releaseDate,
			configurationEntity.images.secureBaseUrl + configurationEntity.images.posterSizes[3] + movieEntity.posterPath
		)
	}

	override fun mapToEntity(domain: Movie) = throw UnsupportedMappingException()

}

@Singleton
class CastMapper @Inject constructor() : Mapper<Cast, Pair<ConfigurationEntity, CastEntity>> {
	override fun mapToDomain(entity: Pair<ConfigurationEntity, CastEntity>): Cast {
		val configurationEntity = entity.first
		val castEntity = entity.second

		return Cast(
			castEntity.castId,
			castEntity.name,
			castEntity.character,
			configurationEntity.images.secureBaseUrl + configurationEntity.images.profileSizes[3] + castEntity.profilePath
		)
	}

	override fun mapToEntity(domain: Cast) = throw UnsupportedMappingException()

}
