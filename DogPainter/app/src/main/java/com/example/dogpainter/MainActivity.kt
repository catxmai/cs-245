package com.example.dogpainter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogpainter.ColorFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val colorArray = arrayOf(
        R.color.brown, R.color.gold,
        R.color.black, R.color.white,
        R.color.grey, R.color.red
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dog_image.setImageResource(R.drawable.dog)
        val menuFragment=
            supportFragmentManager.findFragmentById(R.id.menu_fragment) as ColorFragment
        menuFragment.clickLambda= switchColorLambda
    }

    val switchColorLambda: (Int) -> Unit = {
        dog_image.setBackgroundColor(getColor(colorArray[it]))
    }


}