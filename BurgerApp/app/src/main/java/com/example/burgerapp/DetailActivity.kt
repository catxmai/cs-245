package com.example.burgerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.burgerapp.MainActivity.Companion.imageId
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_name_text.text=intent.getStringExtra(MainActivity.name)
        description_text.text=intent.getStringExtra(MainActivity.description)
        val drawableResourceId = resources.
        getIdentifier(intent.getStringExtra(MainActivity.imageId), "drawable", "com.example.burgerapp")
        profile_img.setImageResource(drawableResourceId)
    }
}


