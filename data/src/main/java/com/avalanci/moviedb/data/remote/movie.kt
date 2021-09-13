package com.avalanci.moviedb.data.remote

import com.avalanci.moviedb.data.entity.ConfigurationEntity
import com.avalanci.moviedb.data.entity.CreditsEntity
import com.avalanci.moviedb.data.entity.MovieEntity
import com.avalanci.moviedb.data.entity.NowPlayingEntity
import com.avalanci.moviedb.data.entity.VideosEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

	@GET("configuration")
	suspend fun fetchConfiguration(): ConfigurationEntity

	@GET("movie/popular")
	suspend fun fetchPopularMovies(@Query("page") page: Int = 1): NowPlayingEntity

	@GET("movie/{movieId}")
	suspend fun fetchMovie(@Path("movieId") movieId: Int): MovieEntity

	@GET("movie/{movieId}/credits")
	suspend fun fetchCredits(@Path("movieId") movieId: Int): CreditsEntity

	@GET("movie/{movieId}/videos")
	suspend fun fetchVideos(@Path("movieId") movieId: Int): VideosEntity

}
