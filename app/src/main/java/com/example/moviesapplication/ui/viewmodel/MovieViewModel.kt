package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.Genre
import com.example.moviesapplication.data.Genres
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.domain.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val movieRepository  = MovieRepository()
    private val movie = MutableLiveData<Movie>()
    private val genres = MutableLiveData<Genres>()

    fun getMoviesLiveData() : LiveData<Movie> = movie
    fun getGenreList() : LiveData<Genres> = genres

    fun getMovieData(page : String){
        movieRepository.getMovieData("",movie)
    }
    fun getGenres(){
        movieRepository.getGenres(genres)
    }
}