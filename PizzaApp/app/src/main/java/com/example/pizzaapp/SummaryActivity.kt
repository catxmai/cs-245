package com.example.pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        size_view.text=intent.getStringExtra(ToppingActivity.pizza_size)
        num_view.text=intent.getStringExtra(ToppingActivity.pizza_num)
        var tops = intent.getStringArrayListExtra(ToppingActivity.topping)
        var toppings = ""
        tops?.forEach {
            toppings+=it + "\n"
        }
        topping_view.text=toppings
    }
}