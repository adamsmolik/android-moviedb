package com.avalanci.moviedb.data.remote

import com.avalanci.moviedb.data.entity.ConfigurationEntity
import com.avalanci.moviedb.data.entity.NowPlayingEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

	@GET("configuration")
	suspend fun fetchConfiguration(): ConfigurationEntity

	@GET("movie/popular")
	suspend fun fetchPopularMovies(@Query("page") page: Int = 1): NowPlayingEntity

}
