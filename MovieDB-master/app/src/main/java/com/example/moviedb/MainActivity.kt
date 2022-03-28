package com.example.moviedb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var viewAdapter: MovieAdapter
    val viewModel:MovieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController=findNavController(R.id.nav_host_fragment)

        viewModel.fetchUpcome()
        viewModel.fetchNowPlay()
        viewAdapter = MovieAdapter(viewModel.nowshowingMovie.value!!)
        viewAdapter.notifyDataSetChanged()
        viewModel.savedMovies.value = viewModel.databaseSaved?.movieDAO()?.getMovieList() as ArrayList<Movie>

    }

}


