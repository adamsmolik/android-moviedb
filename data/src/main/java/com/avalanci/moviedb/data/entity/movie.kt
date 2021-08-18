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
	val overview: String
)
