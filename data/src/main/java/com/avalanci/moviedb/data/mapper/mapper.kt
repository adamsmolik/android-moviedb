package com.avalanci.moviedb.data.mapper

interface Mapper<Domain, Entity> {

	fun mapToDomain(entity: Entity): Domain

	fun mapToEntity(domain: Domain): Entity

}

class UnsupportedMappingException : UnsupportedOperationException("Mapping in this way isn't supported.")
