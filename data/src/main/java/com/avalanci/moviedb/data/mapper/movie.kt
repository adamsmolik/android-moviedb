package com.avalanci.moviedb.data.mapper

import com.avalanci.moviedb.data.entity.ConfigurationEntity
import com.avalanci.moviedb.data.entity.MovieEntity
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
			configurationEntity.images.baseUrl + configurationEntity.images.posterSizes[3]
		)
	}

	override fun mapToEntity(domain: Movie) = throw UnsupportedMappingException()

}
