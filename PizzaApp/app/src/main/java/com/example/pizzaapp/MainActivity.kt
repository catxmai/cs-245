package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_topping.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    companion object{
        val pizza_num = "pizza_num"
        val pizza_size = "pizza_size"
        val seek_bar = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        number_bar.setOnSeekBarChangeListener(this)

        next_button_1.setOnClickListener {
            val num=pizza_num_button.text.toString()
            val size=when{
                small_button.isChecked->"Small"
                med_button.isChecked->"Medium"
                large_button.isChecked->"Large"
                else->"Not provided"
            }

            val intent=Intent(this, ToppingActivity::class.java)
            intent.putExtra(pizza_num, num)
            intent.putExtra(pizza_size, size)
            startActivity(intent)
        }
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        seekview.text=p1.toString()
    }
    override fun onStartTrackingTouch(p0: SeekBar?) { }
    override fun onStopTrackingTouch(p0: SeekBar?) { }

}