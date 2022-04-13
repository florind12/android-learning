package com.example.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.movie_details.SingleMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener {
            val intent = Intent(this, SingleMovie::class.java)
            intent.putExtra("movie_name", searchInput.text.toString())
            this.startActivity(intent)
        }
    }

}