package com.example.moviesapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.data.Movie

class MovieAdapter(private val movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        val movieItem_imageView : ImageView
        val movieItem_itemName : TextView
        val movieItem_genres : TextView

        init {
            movieItem_genres = view.findViewById(R.id.movieList_movieGenres)
            movieItem_imageView = view.findViewById(R.id.movieList_imageView)
            movieItem_itemName = view.findViewById(R.id.movieList_movieName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieItem_itemName.text = movies[position].title
        holder.movieItem_genres.text = ""

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovieList(movieList : List<Movie>){
        if(movies.isNotEmpty()){
            movies.clear()
            movies.addAll(movieList)
            notifyDataSetChanged()
        }
        movies.addAll(movieList)
        notifyDataSetChanged()
    }
}