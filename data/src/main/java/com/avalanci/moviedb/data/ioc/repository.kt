package com.avalanci.moviedb.data.ioc

import com.avalanci.moviedb.data.repository.MovieRepositoryImpl
import com.avalanci.moviedb.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

	@Binds
	abstract fun bindMovieRepository(implementation: MovieRepositoryImpl): MovieRepository

}
