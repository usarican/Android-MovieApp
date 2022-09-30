package com.example.moviesapplication.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id") val id : Int,
    @SerializedName("title")
    val title : String,
    val overview : String,
    val genres : List<Genre>,
    @SerializedName("release_date")
    val date : String,
    @SerializedName("poster_path")
    val image : String
)
