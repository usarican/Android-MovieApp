package com.example.moviesapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.data.model.Genre
import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Result
import com.example.moviesapplication.databinding.MovieItemBinding
import com.example.moviesapplication.ui.view.MovieFragmentDirections
import com.example.moviesapplication.ui.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var results: List<Result>,
    private var _genres : Genres
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), MovieClickListener {

    private var filterResult = listOf<Result>()

    class MovieViewHolder(var view : MovieItemBinding) : RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MovieItemBinding>(inflater,R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.result = results[position]
        holder.view.genres = _genres
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun updateMovieList(resultList : List<Result>){
        results = resultList
        notifyDataSetChanged()
    }


    fun setGenreList(genres : Genres){
        _genres = genres
    }


    override fun onMovieClicked(v: View) {
        val movieItemId = v.findViewById<TextView>(R.id.movieItem_id)
        val id = Integer.parseInt(movieItemId.text.toString())
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}