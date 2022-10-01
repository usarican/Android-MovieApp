package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.model.MovieDetails
import com.example.moviesapplication.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val movieDetail = MutableLiveData<MovieDetails>()
    fun getMovieDetailLiveData() : LiveData<MovieDetails> = movieDetail

    fun getMovieDetail(movie_id : Int){
        movieRepository.getMovieDetails(movie_id,movieDetail)
    }

}