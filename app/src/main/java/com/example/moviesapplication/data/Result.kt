package com.example.moviesapplication.data

import com.google.gson.annotations.SerializedName

data class Result(
   @SerializedName("id") val id: Int,
   val title : String,
   @SerializedName("poster_path") val image : String,
   @SerializedName("vote_average") val score : Double,
   @SerializedName("genre_ids") val genres : List<Int>,
)
