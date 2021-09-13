package com.avalanci.moviedb.domain.model


data class Movie(
	val id: Int,
	val title: String,
	val overview: String?,
	val releaseDate: String,
	val posterUrl: String,
	val videoId: String?
)

data class Cast(
	val id: Int,
	val name: String,
	val character: String,
	val imageUrl: String
)
