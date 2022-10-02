package com.example.moviesapplication.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviesapplication.R
import com.example.moviesapplication.data.model.Genre
import com.example.moviesapplication.data.model.MovieDetails
import com.example.moviesapplication.databinding.FragmentMovieDetailsBinding
import com.example.moviesapplication.ui.viewmodel.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailsBinding
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
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie_details, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            movieId = MovieDetailsFragmentArgs.fromBundle(it).id
        }

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.getMovieDetail(movieId)

        getMovieDetailsLiveData()
        observeProgressBar()

    }

    private fun getMovieDetailsLiveData(){
        with(binding){
            movieDetailsViewModel.getMovieDetailLiveData().observe(viewLifecycleOwner, object : Observer<MovieDetails> {
                override fun onChanged(t: MovieDetails?) {
                    if(t != null){
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500"+t.image)
                            .into(movieDetailsImageView)
                        movieDetailTitle.text = t.title
                        movieDetailDetail.text = t.overview
                        movieDetailReleaseDate.text = t.date
                        movieDetailGenres.text = getGenreText(t.genres)
                    }
                }
            })

            backButton.setOnClickListener { view ->
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    private fun observeProgressBar(){
        movieDetailsViewModel.getMovieIsLoading().observe(viewLifecycleOwner, Observer {
            if(it){
                binding.moviedetailProgressbar.visibility = View.VISIBLE
                binding.movieDetailsImageView.visibility = View.INVISIBLE
                binding.movieDetailInfo.visibility = View.INVISIBLE
                binding.customLinearLayout.visibility = View.INVISIBLE
            }else {
                binding.moviedetailProgressbar.visibility = View.INVISIBLE
                binding.movieDetailsImageView.visibility = View.VISIBLE
                binding.movieDetailInfo.visibility = View.VISIBLE
                binding.customLinearLayout.visibility = View.VISIBLE
            }
        })
    }

    private fun getGenreText(genreList : List<Genre>) : String {
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