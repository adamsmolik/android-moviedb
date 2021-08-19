package com.avalanci.moviedb.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
	val id: Int,
	val title: String,
	val overview: String,
	val posterUrl: String
) : Parcelable
