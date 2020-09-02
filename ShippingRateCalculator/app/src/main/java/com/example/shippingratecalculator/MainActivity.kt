package com.example.shippingratecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        if(p0?.id==clear_button.id){
            itemList.clear()
            showItems()
        }
    }
    val itemList =ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_button.setOnClickListener {
            val name = name_text.text.toString()
            val weight = weight_text.text.toString()
            Log.d("package", "$name, $weight")
            Log.d("price rate", Item.ItemPrice.joinToString { "$${it}" })
            if (name.isNotBlank() && weight.isNotEmpty()) {
                val item = Item(name = name, weight = weight.toDouble())
                itemList.add(item)
            }
            showItems()
        }
        clear_button.setOnClickListener(this)
    }
    fun showItems(){
        var sumCost = 0.0
        for (item in itemList){
            sumCost+=item.price
        }
        val sumWeight=itemList.sumByDouble { it.weight }

        var information="Total weight: $sumWeight, Total cost: $sumCost\n"
        for (index in 0 until itemList.size){
            information += itemList[index].getInformation(index) + "\n"
        }
        info_textview.text=information
        name_text.text.clear()
        weight_text.text.clear()

    }



}