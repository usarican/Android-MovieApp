package com.example.moviesapplication.data.remote

import com.example.moviesapplication.data.Genre
import com.example.moviesapplication.data.Genres
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.data.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    // URL : https://api.themoviedb.org/3/movie/popular?api_key=1c43b969c6e3c21291826ddc75a81aa1

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "1c43b969c6e3c21291826ddc75a81aa1",
        @Query("page") query : String
    ) : Call<Movie>

    @GET("3/genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String = "1c43b969c6e3c21291826ddc75a81aa1"
    ) : Call<Genres>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey: String = "1c43b969c6e3c21291826ddc75a81aa1",
    ) : Call<MovieDetails>
}