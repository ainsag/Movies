package com.example.movies.data.page

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val overview: String?,
    val poster_path: String,
    val origin_title: String
): Parcelable {
    val  baseUrl get() = "https://image.tmdb.org/t/p/w500"
}