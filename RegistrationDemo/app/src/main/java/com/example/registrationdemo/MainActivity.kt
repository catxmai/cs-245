package com.example.registrationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val user = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val setUserLambda: (String, String, String) -> Unit = { u, p, d ->
        user.username = u
        user.password = p
        user.dob = d
    }
}
