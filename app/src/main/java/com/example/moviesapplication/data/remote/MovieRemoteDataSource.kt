package com.example.moviesapplication.data.remote

import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Movie
import com.example.moviesapplication.data.model.MovieDetails
import retrofit2.Call
import javax.inject.Inject


class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    fun getPopulerMovies(page : String) : Call<Movie> =
        movieService.getPopularMovies(page = page)

    fun getGenres() : Call<Genres> =
        movieService.getGenres()

    fun getMovieDetails(movieId : Int) : Call<MovieDetails> =
        movieService.getMovieDetails(movie_id = movieId)
}