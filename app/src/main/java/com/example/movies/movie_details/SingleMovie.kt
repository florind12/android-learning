package com.example.movies.movie_details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.api.MovieDBClient
import com.example.movies.data.repository.NetworkState
import com.example.movies.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*

class SingleMovie : AppCompatActivity() {
    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val apiService = MovieDBClient.getClient()
        movieDetailsRepository = MovieDetailsRepository(apiService)

        val movieName:String = intent.getStringExtra("movie_name").toString()
        viewModel = ViewModelProvider(this, SingleMovieViewFactory(movieDetailsRepository, movieName))[SingleMovieViewModel::class.java]

        viewModel.movieDetails.observe(this) {
            bindUI(it)
        }

        viewModel.networkState.observe(this) {
            progressBar.visibility = if (it === NetworkState.LOADING) View.VISIBLE else View.GONE
            errorMessage.visibility = if (it === NetworkState.ERROR) View.VISIBLE else View.GONE
        }

    }

    private fun bindUI(it: MovieDetails) {
        val posterImg = findViewById<ImageView>(R.id.movie_poster)
        Glide.with(this).load(it.Poster).into(posterImg)

        findViewById<TextView>(R.id.movie_title).apply {
            "Movie title: ${it.Title}".also { text = it }
        }
    }
}