package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Movie
import com.example.moviesapplication.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val movie = MutableLiveData<Movie>()
    private val genres = MutableLiveData<Genres>()

    fun getMoviesLiveData() : LiveData<Movie> = movie
    fun getGenreList() : LiveData<Genres> = genres

    fun getMovieData(page : String){
        movieRepository.getMovieData(page,movie)
    }
    fun getGenres(){
        movieRepository.getGenres(genres)
    }
}