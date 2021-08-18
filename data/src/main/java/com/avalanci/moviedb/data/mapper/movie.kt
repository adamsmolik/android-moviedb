package com.avalanci.moviedb.data.mapper

import com.avalanci.moviedb.data.entity.MovieEntity
import com.avalanci.moviedb.domain.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMapper @Inject constructor() : Mapper<Movie, MovieEntity> {

	override fun mapToDomain(entity: MovieEntity) = Movie(
		entity.id,
		entity.title,
		entity.overview
	)

	override fun mapToEntity(domain: Movie) = throw UnsupportedMappingException()

}
