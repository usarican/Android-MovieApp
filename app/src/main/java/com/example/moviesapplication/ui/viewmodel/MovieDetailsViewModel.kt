package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.data.Result

class MovieDetailsViewModel : ViewModel() {
    private val movie = MutableLiveData<Result>()
    fun getMovieLiveData() : LiveData<Result> = movie

}