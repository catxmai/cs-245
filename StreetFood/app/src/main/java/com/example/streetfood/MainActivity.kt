package com.example.streetfood


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity : AppCompatActivity() {

    val viewModel:FoodViewModel by viewModels<FoodViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (viewModel.restaurantList.value?.size==0) {
            loadData()
            viewModel.restaurantList.value = restaurantArray
        }
    }

    var restaurantArray = ArrayList<Restaurant>()
    fun loadData() {
        val dataString =
            resources.openRawResource(R.raw.data).bufferedReader()
                .use { it.readText() }// read the entire file as a string
        var lines = dataString.trim().split("\n") // split each line
        lines = lines.subList(1, lines.size) // get rid of the header line
        lines.forEach {
            restaurantArray.add(makeRestaurant(it))
        }
    }

    fun makeRestaurant(line: String): Restaurant {
        val cells = line.split(",")
        val rest = Restaurant(
            cells[0],
            cells[1],
            cells[2],
            cells[3],
            cells[4],
            cells[5],
            "",
            0.toFloat()
        )
        return rest
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_menu -> {
                findNavController(nav_host_frag).navigate(R.id.action_global_styleFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}