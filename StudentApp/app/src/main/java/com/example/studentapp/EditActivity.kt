 package com.example.studentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*

 class EditActivity : AppCompatActivity() {
    companion object{
        val name_key="name_key"
        val gender_key="gender_key"
        val sport_key="sport_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        save_button.setOnClickListener {
            val name=name_text.text.toString()
            val gender=when{
                male_button.isChecked->"Male"
                female_button.isChecked->"Female"
                else->"Not provided"
            }
            val sports = ArrayList<String>()

            if (basketball_button.isChecked)
                sports.add("Basketball")
            if (football_button.isChecked)
                sports.add("Football")
            if (baseball_button.isChecked)
                sports.add("Baseball")

            val intent=Intent(this, MainActivity::class.java)
            intent.putExtra(name_key, name)
            intent.putExtra(gender_key, gender)
            intent.putExtra(sport_key, sports)
            startActivity(intent)
        }
    }
}