package com.example.moviesapplication.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapplication.data.model.Result
import com.example.moviesapplication.data.remote.MovieRemoteDataSource
/*class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource
) : PagingSource<Int,Result>(){
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: 1

        return try {
            val response = remoteDataSource.getPopulerMovies(position.toString())
            val result = response.results
            Log.d("MoviePagingSource",result.toString())

            LoadResult.Page(
                data = result,
                prevKey = if(position == 1) null else position -1,
                nextKey = if(result.isEmpty()) null else position + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }

}*/