package com.example.moviesapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

import com.example.moviesapplication.data.model.Genre



@BindingAdapter("getGenreText","getGenreList")
fun TextView.getGenreText(genreIds : List<Int>,genres : List<Genre>)  {
    var genreString = ""
    genres?.let {
        try {
            val genreId = genreIds[0]
            for(genre in genres){
                genre?.let {
                    if(genreId == genre.id){
                        genreString += (genre.name + " ")
                    }
                }
            }
        }catch (e: Exception){
            e.stackTrace
        }
    }
    this.text = genreString
}