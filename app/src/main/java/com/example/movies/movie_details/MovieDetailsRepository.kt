package com.example.movies.movie_details

import androidx.lifecycle.LiveData
import com.example.movies.data.api.MovieDBInterface
import com.example.movies.data.repository.MovieDetailsNetworkDataSource
import com.example.movies.data.repository.NetworkState
import com.example.movies.data.vo.MovieDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: MovieDBInterface) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieName: String):LiveData<MovieDetails> {
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieName)

        return movieDetailsNetworkDataSource.movieDetailsResponse
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}