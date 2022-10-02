package com.example.moviesapplication.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Movie
import com.example.moviesapplication.data.model.MovieDetails
import com.example.moviesapplication.data.model.Result
import com.example.moviesapplication.data.remote.MovieRemoteDataSource
//import com.example.moviesapplication.paging.MoviePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {

    private val movieIsLoading = MutableLiveData<Boolean>()
    private val movieIsError = MutableLiveData<Boolean>()

    fun getIsLoadingData() : LiveData<Boolean> = movieIsLoading
    fun getIsErrorData() : LiveData<Boolean> = movieIsError

   /* fun getMovieResult() : LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieRemoteDataSource) }
        ).liveData
    }*/

    fun getMovieData(page : String, liveData: MutableLiveData<Movie>) {
        movieIsLoading.postValue(true)
        movieIsError.postValue(false)
        movieRemoteDataSource.getPopulerMovies(page = page).enqueue(object: Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("MovieRepository", response.body().toString())
                liveData.postValue(response.body())
                movieIsLoading.postValue(false)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieIsError.postValue(true)
                liveData.postValue(null)
            }

        })
    }

    fun getGenres(liveData: MutableLiveData<Genres>){
        movieRemoteDataSource.getGenres().enqueue(object : Callback<Genres>{
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
        movieIsLoading.postValue(true)
        movieIsError.postValue(false)
        movieRemoteDataSource.getMovieDetails(movie_id).enqueue(object : Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("MovieRepository", response.body().toString())
                liveData.postValue(response.body())
                movieIsLoading.postValue(false)
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                movieIsError.postValue(true)
                liveData.postValue(null)
            }

        })
    }

    fun getUpcomingMovies(page : String ,liveData: MutableLiveData<Movie>){
        movieIsLoading.postValue(true)
        movieIsError.postValue(false)
        movieRemoteDataSource.getUpcomingMovies(page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("MovieRepositoryU", response.body().toString())
                liveData.postValue(response.body())
                movieIsLoading.postValue(false)
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieIsError.postValue(true)
                liveData.postValue(null)
            }

        })
    }

    fun getTopRatedMovies(page : String,liveData: MutableLiveData<Movie>){
        movieIsLoading.postValue(true)
        movieIsError.postValue(false)
        movieRemoteDataSource.getTopRatedMovies(page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("MovieRepositoryT", response.body().toString())
                liveData.postValue(response.body())
                movieIsLoading.postValue(false)
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieIsError.postValue(true)
                liveData.postValue(null)
            }

        })
    }

    fun getNowPlayingMovies(page : String,liveData: MutableLiveData<Movie>){
        movieIsLoading.postValue(true)
        movieIsError.postValue(false)
        movieRemoteDataSource.getNowPlayingMovies(page).enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                Log.d("MovieRepositoryN", response.body().toString())
                liveData.postValue(response.body())
                movieIsLoading.postValue(false)
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieIsError.postValue(true)
                liveData.postValue(null)
            }

        })
    }
}