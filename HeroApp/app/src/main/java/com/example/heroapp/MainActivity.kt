package com.example.heroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val hero = Hero()

    val setHeroLambda: (String, String) -> Unit = { d,h ->
        hero.background=d
        hero.foreground=h
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_menu -> {
                findNavController(nav_host_frag).navigate(R.id.action_global_dayFragment)
                true
            }
            R.id.random_menu -> {
                hero.background = listOf("day", "night").random()
                hero.foreground = listOf("superman", "batman", "ironman").random()
                findNavController(nav_host_frag).navigate(R.id.action_global_posterFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}