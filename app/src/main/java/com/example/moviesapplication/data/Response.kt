package com.example.moviesapplication.data

import com.google.gson.annotations.SerializedName

data class Response(
    private val page : String,
    @SerializedName("results")
    private val movies : List<Movie>
)
