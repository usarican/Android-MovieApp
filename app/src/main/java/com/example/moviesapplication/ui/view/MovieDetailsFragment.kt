package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.R
import com.example.moviesapplication.ui.viewmodel.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.movieDetails_imageView)
        val title = view.findViewById<TextView>(R.id.movieDetail_title)
        val overView = view.findViewById<TextView>(R.id.movieDetail_detail)
        val genres = view.findViewById<TextView>(R.id.movieList_movieGenres)
        val releaseDate = view.findViewById<TextView>(R.id.movieDetail_releaseDate)

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        //movieDetailsViewModel.getData()

        movieDetailsViewModel.getMovieLiveData().observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                //title.text = movie.title
                //overView.text = movie.overview
                //releaseDate.text = movie.releaseDate
            }
        })

        arguments?.let {
            movieId = MovieFragmentArgs.fromBundle(it).id
        }


    }


}