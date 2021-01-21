package com.example.mvvmcoroutinesdatabindingrecyclerviewnavigationretrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MoviesPogoResult(
    val results: List<Result>
)
@Parcelize
data class Result(
    val id: Int,
    val popularity: Double,
    val backdrop_path: String,
    val release_date: String,
    val original_title: String
    ): Parcelable
