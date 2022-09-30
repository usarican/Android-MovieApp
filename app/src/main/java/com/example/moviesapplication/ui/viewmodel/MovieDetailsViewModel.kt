package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.MovieDetails
import com.example.moviesapplication.data.Result
import com.example.moviesapplication.domain.repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {

    private val movieRepository  = MovieRepository()
    private val movieDetail = MutableLiveData<MovieDetails>()
    fun getMovieDetailLiveData() : LiveData<MovieDetails> = movieDetail

    fun getMovieDetail(movie_id : Int){
        movieRepository.getMovieDetails(movie_id,movieDetail)
    }

}