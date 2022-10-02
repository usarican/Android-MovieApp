package com.example.moviesapplication.ui.adapter

import android.view.View
import com.example.moviesapplication.databinding.MovieItemBinding

interface MovieClickListener {
    fun onMovieClicked(v : View)
}