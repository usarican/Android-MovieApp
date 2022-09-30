package com.example.moviesapplication.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviesapplication.data.Genre
import com.example.moviesapplication.data.Genres
import com.example.moviesapplication.data.Movie
import com.example.moviesapplication.data.MovieDetails
import com.example.moviesapplication.data.remote.MovieRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    val movieRemoteDataSource = MovieRemoteDataSource()


    fun getMovieData(page : String, liveData: MutableLiveData<Movie>) {
        movieRemoteDataSource.service.getPopularMovies(query = page).enqueue(object: Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("MovieRepository", response.body().toString())
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getGenres(liveData: MutableLiveData<Genres>){
        movieRemoteDataSource.service.getGenres().enqueue(object : Callback<Genres>{
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                Log.d("MovieRepository", response.body().toString())
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getMovieDetails(movie_id : Int,liveData: MutableLiveData<MovieDetails>){
        movieRemoteDataSource.service.getMovieDetails(movie_id).enqueue(object : Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("MovieRepository", response.body().toString())
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}