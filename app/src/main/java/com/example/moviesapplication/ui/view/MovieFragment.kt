package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Movie
import com.example.moviesapplication.data.model.Result
import com.example.moviesapplication.ui.adapter.MovieAdapter
import com.example.moviesapplication.ui.viewmodel.MovieViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var page = 1
    private val layoutManager = GridLayoutManager(context,2)
    private lateinit var movieViewModel : MovieViewModel
    private val movieAdapter = MovieAdapter(arrayListOf(), listOf())
    private var movieList = mutableListOf<Result>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        observeLiveData()
        movieViewModel.getMovieData(page.toString())
        movieViewModel.getGenres()


        val movieRecyclerView = view.findViewById<RecyclerView>(R.id.movie_list)
        val searchMovie = view.findViewById<TextInputEditText>(R.id.movie_searchEditText)
        movieRecyclerView.layoutManager = layoutManager
        movieRecyclerView.adapter = movieAdapter

        searchMovie.addTextChangedListener {
            filterList(it.toString())
        }

    }

    private fun filterList(p0: String) {
        var filteredList = mutableListOf<Result>()
        if(p0.isNotEmpty()){
            for (movie in movieList){
                if(movie.title.lowercase().contains(p0.lowercase())){
                    filteredList.add(movie)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show()
            }
            movieAdapter.updateMovieList(filteredList)
        }
        else {
            movieAdapter.updateMovieList(movieList)
        }


    }

    fun observeLiveData(){
        movieViewModel.getMoviesLiveData().observe(viewLifecycleOwner,object : Observer<Movie>{
            override fun onChanged(t: Movie?) {
                if(t!= null){
                    movieAdapter.updateMovieList(t.results)
                    if(movieList.isEmpty()){
                        movieList.addAll(t.results)
                    }else {
                        movieList.clear()
                        movieList.addAll(t.results)
                    }
                }
            }
        })
        movieViewModel.getGenreList().observe(viewLifecycleOwner,object : Observer<Genres>{
            override fun onChanged(t: Genres?) {
                if(t!=null){
                    movieAdapter.setGenreList(t.genres)
                }
            }

        })
    }

}