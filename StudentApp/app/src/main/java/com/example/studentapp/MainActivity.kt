package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_button.setOnClickListener {
            val intent=Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        name_text.text=intent.getStringExtra(EditActivity.name_key)
        gender_text.text=intent.getStringExtra(EditActivity.gender_key)
        var sports = intent.getStringArrayListExtra(EditActivity.sport_key)
        var hobbies = ""
        sports?.forEach {
            hobbies+=it + "\n"
        }
        hobby_text.text=hobbies
    }
}