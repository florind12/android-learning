package com.example.movies.data.vo

data class MovieDetails(
    val Country: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Rated: String,
    val Poster: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Runtime: String,
    val Title: String,
    val Year: String,
     val imdbRating: String,
    val imdbVotes: String
)