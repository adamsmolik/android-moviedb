package com.avalanci.moviedb.data.entity

import com.squareup.moshi.Json

data class NowPlayingEntity(
	@Json(name = "results")
	val results: List<MovieEntity>,
	@Json(name = "page")
	val page: Int,
	@Json(name = "total_pages")
	val totalPages: Int,
	@Json(name = "total_results")
	val totalResults: Int
)

data class MovieEntity(
	@Json(name = "id")
	val id: Int,
	@Json(name = "title")
	val title: String,
	@Json(name = "overview")
	val overview: String?,
	@Json(name = "release_date")
	val releaseDate: String,
	@Json(name = "poster_path")
	val posterPath: String
)

data class CreditsEntity(
	val id: Int,
	val cast: List<CastEntity>
)

data class CastEntity(
	@Json(name = "cast_id")
	val castId: Int,
	val character: String,
	@Json(name = "credit_id")
	val creditId: String,
	val gender: Int?,
	val name: String,
	val order: Int,
	@Json(name = "profile_path")
	val profilePath: String?
)

data class VideosEntity(
	val id: Int?,
	val results: List<VideoEntity>?
)

data class VideoEntity(
	val iso_639_1: String?,
	val iso_3166_1: String?,
	val name: String?,
	val key: String?,
	val site: String?,
	val size: Int?,
	val type: String?,
	val official: Boolean?,
	@Json(name = "published_at")
	val publishedAt: String?,
	val id: String?
)
