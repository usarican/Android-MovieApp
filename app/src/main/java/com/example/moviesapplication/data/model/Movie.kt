package com.example.moviesapplication.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page")
    val page : String,
    @SerializedName("results")
    val results : List<Result>
)
