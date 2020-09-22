package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_topping.*

class ToppingActivity : AppCompatActivity() {
    companion object {
        val topping = "toppings"
        val pizza_size = "size"
        val pizza_num = "num"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topping)

        size_textview.text = intent.getStringExtra(MainActivity.pizza_size)
        val num = intent.getStringExtra(MainActivity.pizza_num)

        next_button_2.setOnClickListener {
            val toppings = ArrayList<String>()

            if (pep_button.isChecked)
                toppings.add("Pepperoni")
            if (bac_button.isChecked)
                toppings.add("Bacon")
            if (sau_button.isChecked)
                toppings.add("Sausage")
            if (pin_button.isChecked)
                toppings.add("Pineapple")
            if (mus_button.isChecked)
                toppings.add("Mushroom")

            val intent= Intent(this, SummaryActivity::class.java)
            intent.putExtra(topping, toppings)
            intent.putExtra(pizza_size, size_textview.text.toString())
            intent.putExtra(pizza_num, num)
            startActivity(intent)
        }
    }
}
