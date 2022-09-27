package com.example.moviesapplication.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    val title : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("released_date")
    val releaseDate : String,
    @SerializedName("genre_ids")
    val genres : List<Genre>,
    @SerializedName("poster_path")
    val image : String,
    @SerializedName("vote_average")
    val score : Double
)
