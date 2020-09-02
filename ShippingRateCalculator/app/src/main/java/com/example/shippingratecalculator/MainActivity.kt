package com.example.shippingratecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val itemList =ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_button.setOnClickListener {
            val name=name_text.text.toString()
            val weight=weight_text.text.toString()
            Log.d("package", "$name, $weight")
            Log.d("price rate", Item.ItemPrice.joinToString { "$${it}" })
            if (name.isNotBlank() && weight.isNotEmpty()){
                val item = Item(name=name, weight=weight.toDouble())
                itemList.add(item)
            }
        }

    }
}