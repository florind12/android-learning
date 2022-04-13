package com.example.movies.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SingleMovieViewFactory(private val movieRepository:MovieDetailsRepository, private val movieName: String): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SingleMovieViewModel(movieRepository, movieName) as T

}