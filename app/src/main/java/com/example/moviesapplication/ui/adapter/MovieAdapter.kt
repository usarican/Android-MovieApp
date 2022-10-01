package com.example.moviesapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.data.Genre
import com.example.moviesapplication.data.Result
import com.example.moviesapplication.ui.view.MovieFragmentDirections
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var results: List<Result>,
    private var genreList : List<Genre>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var filterResult = listOf<Result>()

    class MovieViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        val movieItem_imageView : ImageView
        val movieItem_itemName : TextView
        val movieItem_genres : TextView
        val movieItem_score : TextView

        init {
            movieItem_genres = view.findViewById(R.id.movieList_movieGenres)
            movieItem_imageView = view.findViewById(R.id.movieList_imageView)
            movieItem_itemName = view.findViewById(R.id.movieList_movieName)
            movieItem_score = view.findViewById(R.id.movieList_score)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieItem_itemName.text = results[position].title
        holder.movieItem_score.text = results[position].score.toString()
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500"+results[position].image)
            .into(holder.movieItem_imageView)
        val genreIds = results[position].genres
        val id = results[position].id
        holder.movieItem_genres.text =  getGenreText(genreIds,genreList)


        holder.view.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun updateMovieList(resultList : List<Result>){
        results = resultList
        notifyDataSetChanged()
    }


    fun setGenreList(list:List<Genre>){
        genreList = list
        println("GenreList : $genreList")
    }


    fun getGenreText(genreIds : List<Int>,genreList : List<Genre?>) : String {
        var genreString = ""
        try {
            val genreId = genreIds[0]
            for(genre in genreList){
                genre?.let {
                    if(genreId == genre.id){
                        genreString += (genre.name + " ")
                    }
                }
            }
        }catch (e: Exception){
            e.stackTrace
        }
        return genreString
    }
}