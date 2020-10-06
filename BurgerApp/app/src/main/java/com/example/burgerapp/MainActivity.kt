package com.example.burgerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    companion object{
        val imageId = "q"
        val name = "d"
        val description = "a"
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        updateList()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    var foodArray = ArrayList<Food>()
    fun loadData() {
        val dataString =
                resources.openRawResource(R.raw.mcdonalds).bufferedReader()
                        .use { it.readText() }// read the entire file as a string
        var lines = dataString.trim().split("\n") // split each line
        lines = lines.subList(1, lines.size) // get rid of the header line
        lines.forEach {
            foodArray.add(makeFood(it))
        }
    }

    fun makeFood(line: String): Food {
        val cells = line.split(";")
        val food = Food(
                cells[0],
                cells[1],
                cells[2],
                cells[3],
                cells[4],
                false
        )
        return food
    }

    lateinit var viewAdapter: RecyclerViewAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listOf("Type", "Name", "Calorie")
        )
        sort_spinner.adapter = adapter
        sort_spinner.onItemSelectedListener = this

        viewManager = LinearLayoutManager(this)
        food_recycler.layoutManager = viewManager

        viewAdapter= RecyclerViewAdapter(foodArray.toTypedArray())
        food_recycler.adapter=viewAdapter
        viewAdapter.clickLambda=foodClickLambda

        calculate_button.setOnClickListener {

            val current=viewAdapter.foodData.filter {
                it.isSelected
            }
            val total_calo=current.sumBy { it.calorie.toInt() }
            val toast=Toast.makeText(this, "Total calories: $total_calo", Toast.LENGTH_LONG)
            toast.show()
        }
        burger_button.setOnClickListener {
            updateList()
        }
        snack_button.setOnClickListener {
            updateList()
        }
        drink_button.setOnClickListener {
            updateList()
        }

    }
    val foodClickLambda:(Food)->Unit={
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra(name, it.name)
        intent.putExtra(description, it.description)
        intent.putExtra(imageId, it.imageId)
        startActivity(intent)
    }

    fun updateList() {
        var burgerArr=foodArray.filter{
            it.type=="Burger"
        }
        var drinkArr=foodArray.filter{
            it.type=="Drink"
        }
        var snackArr=foodArray.filter{
            it.type=="Snack"
        }
        var sort = sort_spinner.selectedItem.toString()
        var aggType = ArrayList<Food>()
        if (burger_button.isChecked)
            aggType.addAll(burgerArr)
        if (drink_button.isChecked)
            aggType.addAll(drinkArr)
        if (snack_button.isChecked)
            aggType.addAll(snackArr)

        if (sort=="Type") {
            aggType.sortBy { it.type }
        }
        if (sort=="Name") {
            aggType.sortBy { it.name }
        }
        if (sort=="Calorie"){
            aggType.sortBy{it.calorie}
        }
        viewAdapter.foodData=aggType.toTypedArray()
        viewAdapter.notifyDataSetChanged()

    }
}