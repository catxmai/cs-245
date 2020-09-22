package com.example.studentapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val gender = intent.getStringExtra(MainActivity.gender)
        profile_img.setImageResource(
            when{
            gender=="Female"->R.drawable.female
            else->R.drawable.male
        })
        name_text.text=intent.getStringExtra(MainActivity.name)
        phone_text.text=intent.getStringExtra(MainActivity.phone)
        email_text.text=intent.getStringExtra(MainActivity.email)
        address_text.text=intent.getStringExtra(MainActivity.address)
    }
}