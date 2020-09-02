package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currString = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0.setOnClickListener{
            currString = currString+"0"
            textView.setText(currString)
        }

        num1.setOnClickListener{
            currString = currString+"1"
            textView.setText(currString)
        }

        num2.setOnClickListener{
            currString = currString+"2"
            textView.setText(currString)
        }

        num3.setOnClickListener{
            currString = currString+"3"
            textView.setText(currString)
        }

        num4.setOnClickListener{
            currString = currString+"4"
            textView.setText(currString)
        }

        num5.setOnClickListener{
            currString = currString+"5"
            textView.setText(currString)
        }

        num6.setOnClickListener{
            currString = currString+"6"
            textView.setText(currString)
        }

        num7.setOnClickListener{
            currString = currString+"7"
            textView.setText(currString)
        }

        num8.setOnClickListener{
            currString = currString+"8"
            textView.setText(currString)
        }

        num9.setOnClickListener{
            currString = currString+"9"
            textView.setText(currString)
        }

        add_button.setOnClickListener{
            currString = currString+"+"
            textView.setText(currString)
        }

        equal_button.setOnClickListener{
            var numbers = currString.split("+")
            var result = numbers.sumBy { it.toInt() }
            textView.setText(result.toString())
            currString = ""
        }

    }
}