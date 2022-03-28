package com.example.moviedb2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.navigation_items, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val viewModel:MovieViewModel by viewModels<MovieViewModel>()
        val viewAdapter = RecyclerViewAdapter(viewModel.getList())
        return when (item.itemId) {
            R.id.action_nowplaying -> {
                viewModel.apiManager.value?.fetchNowPlay()
                viewAdapter.movieData = viewModel.getList().sortedByDescending { it.rating }.toTypedArray()
                viewAdapter.notifyDataSetChanged()
                true
            }
            R.id.action_upcoming -> {
                viewModel.apiManager.value?.fetchUpcome()
                viewAdapter.movieData = viewModel.getList().sortedByDescending { it.rating }.toTypedArray()
                viewAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}