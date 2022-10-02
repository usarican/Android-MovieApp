package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.model.Genre
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
    private val upcomingMovies = MutableLiveData<Movie>()
    private val topRatedMovies = MutableLiveData<Movie>()
    private val nowPlayingmovies = MutableLiveData<Movie>()

    fun getMovieIsLoading() : LiveData<Boolean> = movieRepository.getIsLoadingData()
    fun getMovieIsError() : LiveData<Boolean> = movieRepository.getIsErrorData()

    fun getMoviesLiveData() : LiveData<Movie> = movie
    fun getGenreList() : LiveData<Genres> = genres
    fun getUpcomingMoviesLiveData() : LiveData<Movie> = upcomingMovies
    fun getTopRatedMoviesLiveData() : LiveData<Movie> = topRatedMovies
    fun getNowPlayingMoviesLiveData() : LiveData<Movie> = nowPlayingmovies

    fun getMovieData(page : String) = movieRepository.getMovieData(page,movie)
    fun getGenres() = movieRepository.getGenres(genres)
    fun getUpcomingMovies(page : String) = movieRepository.getUpcomingMovies(page,upcomingMovies)
    fun getTopRatedMovies(page : String) = movieRepository.getTopRatedMovies(page,topRatedMovies)
    fun getNowPlayingMovies(page : String) = movieRepository.getNowPlayingMovies(page,nowPlayingmovies)

}