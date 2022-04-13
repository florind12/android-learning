package com.example.movies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.data.api.MovieDBInterface
import com.example.movies.data.vo.MovieDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkDataSource(private val apiService: MovieDBInterface, private val compositeDisposable: CompositeDisposable) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _movieDetailsResponse = MutableLiveData<MovieDetails>()
    val movieDetailsResponse: LiveData<MovieDetails>
        get() = _movieDetailsResponse

    fun fetchMovieDetails(movieName: String) {
        try {
            _networkState.postValue(NetworkState.LOADING)
            compositeDisposable.add(
                apiService.getMovieDetails(movieName).subscribeOn(Schedulers.io()).subscribe(
                    {
                    _movieDetailsResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        _networkState.postValue(NetworkState.ERROR)
                        it.message?.let { it1 -> Log.e("MovieDetailsNetworkDataSource", it1) }
                    }
                )
            )
        } catch (e: Exception) {
            e.message?.let { Log.e("MovieDetailsNet workDataSource", it) }
            _networkState.postValue(NetworkState.ERROR)
        }
    }
}