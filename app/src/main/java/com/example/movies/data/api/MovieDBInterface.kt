package com.example.movies.data.api

import com.example.movies.data.vo.MovieDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieDBInterface {
    @GET("/")
    fun getMovieDetails(@Query("t") movieName: String): Single<MovieDetails>
}