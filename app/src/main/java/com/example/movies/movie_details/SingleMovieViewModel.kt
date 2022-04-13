package com.example.movies.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.repository.NetworkState
import com.example.movies.data.vo.MovieDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository:MovieDetailsRepository, movieName: String): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieName)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}