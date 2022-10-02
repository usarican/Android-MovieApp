package com.example.moviesapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("downloadImage")
fun ImageView.downloadImage(url : String){
    Picasso.get()
        .load("https://image.tmdb.org/t/p/w500"+ url)
        .into(this)
}