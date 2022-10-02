package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapplication.R
import com.example.moviesapplication.data.model.Genres
import com.example.moviesapplication.data.model.Movie
import com.example.moviesapplication.data.model.Result
import com.example.moviesapplication.databinding.FragmentMovieBinding
import com.example.moviesapplication.ui.adapter.MovieAdapter
import com.example.moviesapplication.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding : FragmentMovieBinding

    private var page = 1
    private val layoutManager = GridLayoutManager(context,2)
    private val movieViewModel : MovieViewModel by viewModels()
    private val movieAdapter = MovieAdapter(emptyList(),Genres(listOf()))
    private var movieListForFiltering = mutableListOf<Result>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel.getMovieData(page.toString())
        movieViewModel.getGenres()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()


        binding.movieList.layoutManager = layoutManager
        binding.movieList.adapter = movieAdapter
        binding.movieList.setHasFixedSize(true)


        setUpSearchMovie()
        clickChips()

    }

    private fun setUpSearchMovie() {
        with(binding){
            movieSearchEditText.addTextChangedListener {
                filterList(it.toString())
            }
        }
    }

    private fun clickChips(){
        with(binding){
            populerChip.setOnClickListener {
                movieViewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer {
                    movieAdapter.updateMovieList(it.results)
                    if(movieListForFiltering.isEmpty()){
                        movieListForFiltering.addAll(it.results)
                    }else {
                        movieListForFiltering.clear()
                        movieListForFiltering.addAll(it.results)
                    }
                })
            }

            upcomingChip.setOnClickListener {
                movieViewModel.getUpcomingMovies(page.toString())
                movieViewModel.getUpcomingMoviesLiveData().observe(viewLifecycleOwner, Observer {
                    movieAdapter.updateMovieList(it.results)
                    if(movieListForFiltering.isEmpty()){
                        movieListForFiltering.addAll(it.results)
                    }else {
                        movieListForFiltering.clear()
                        movieListForFiltering.addAll(it.results)
                    }
                })
            }

            topRatedChip.setOnClickListener {
                movieViewModel.getTopRatedMovies(page.toString())
                movieViewModel.getTopRatedMoviesLiveData().observe(viewLifecycleOwner, Observer {
                    movieAdapter.updateMovieList(it.results)
                    if(movieListForFiltering.isEmpty()){
                        movieListForFiltering.addAll(it.results)
                    }else {
                        movieListForFiltering.clear()
                        movieListForFiltering.addAll(it.results)
                    }
                })
            }

            nowPlayingChip.setOnClickListener {
                movieViewModel.getNowPlayingMovies(page.toString())
                movieViewModel.getNowPlayingMoviesLiveData().observe(viewLifecycleOwner, Observer {
                    movieAdapter.updateMovieList(it.results)
                    if(movieListForFiltering.isEmpty()){
                        movieListForFiltering.addAll(it.results)
                    }else {
                        movieListForFiltering.clear()
                        movieListForFiltering.addAll(it.results)
                    }
                })
            }
        }
    }

    private fun filterList(p0: String) {
        var filteredList = mutableListOf<Result>()
        if(p0.isNotEmpty()){
            for (movie in movieListForFiltering){
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
            movieAdapter.updateMovieList(movieListForFiltering)
        }
    }

    fun observeLiveData(){
        movieViewModel.getMoviesLiveData().observe(viewLifecycleOwner,object : Observer<Movie>{
            override fun onChanged(t: Movie?) {
                if(t!= null){
                    movieAdapter.updateMovieList(t.results)
                    if(movieListForFiltering.isEmpty()){
                        movieListForFiltering.addAll(t.results)
                    }else {
                        movieListForFiltering.clear()
                        movieListForFiltering.addAll(t.results)
                    }
                }
            }
        })
        movieViewModel.getGenreList().observe(viewLifecycleOwner,object : Observer<Genres>{
            override fun onChanged(t: Genres?) {
                if(t!=null){
                    movieAdapter.setGenreList(t)
                }
            }

        })
        movieViewModel.getMovieIsLoading().observe(viewLifecycleOwner, Observer {
            if(it){
                binding.progressbar.visibility = View.VISIBLE
                binding.movieList.visibility = View.INVISIBLE
            }else {
                binding.progressbar.visibility = View.INVISIBLE
                binding.movieList.visibility = View.VISIBLE
            }
        })
    }

}