package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.ui.adapter.MovieAdapter
import com.example.moviesapplication.ui.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private lateinit var movieViewModel : MovieViewModel
    private val movieAdapter = MovieAdapter(arrayListOf())

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
        movieViewModel.getData()

        val movieRecyclerView = view.findViewById<RecyclerView>(R.id.movie_list)
        movieRecyclerView.layoutManager = GridLayoutManager(context,3)
        movieRecyclerView.adapter = movieAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        movieViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                movieAdapter.updateMovieList(movies)
            }
        })
    }

}