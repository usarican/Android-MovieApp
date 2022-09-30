package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviesapplication.R
import com.example.moviesapplication.data.Genre
import com.example.moviesapplication.data.MovieDetails
import com.example.moviesapplication.ui.viewmodel.MovieDetailsViewModel
import com.squareup.picasso.Picasso

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
        val genres = view.findViewById<TextView>(R.id.movieDetail_genres)
        val releaseDate = view.findViewById<TextView>(R.id.movieDetail_releaseDate)
        val backButton = view.findViewById<ImageButton>(R.id.backButton)

        arguments?.let {
            movieId = MovieDetailsFragmentArgs.fromBundle(it).id
        }

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.getMovieDetail(movieId)

        backButton.setOnClickListener { view ->
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieFragment()
            Navigation.findNavController(view).navigate(action)
        }

        movieDetailsViewModel.getMovieDetailLiveData().observe(viewLifecycleOwner, object : Observer<MovieDetails> {
            override fun onChanged(t: MovieDetails?) {
                if(t != null){
                   Picasso.get()
                        .load("https://image.tmdb.org/t/p/w500"+t.image)
                        .into(imageView)
                    title.text = t.title
                    overView.text = t.overview
                    releaseDate.text = t.date
                    genres.text = getGenreText(t.genres)
                }
            }
        })
    }

    fun getGenreText(genreList : List<Genre>) : String {
        var genreString = ""
        for(i in 0 until (genreList.size)) {
            genreString += if(i != (genreList.size - 1)){
                (genreList[i].name + "\n" )
            } else {
                (genreList[i].name )
            }
        }
        return genreString
    }
}