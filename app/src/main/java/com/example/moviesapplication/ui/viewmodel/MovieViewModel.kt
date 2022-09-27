package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.Movie

class MovieViewModel : ViewModel() {
    private val movies = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = movies

    fun getData(){
        val movie1 = Movie("a","b","1", listOf(),"",0.0)
        val movie2 = Movie("b","b","1", listOf(),"",0.0)
        val movie3 = Movie("c","b","1", listOf(),"",0.0)
        val movie4 = Movie("c","b","1", listOf(),"",0.0)
        val movie5 = Movie("c","b","1", listOf(),"",0.0)
        val movie6 = Movie("c","b","1", listOf(),"",0.0)

        val movieList = listOf(movie1,movie2,movie3,movie4,movie5)
        movies.value = movieList
    }
}