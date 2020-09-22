package com.example.studentapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockapp.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object{
        val name = "student"
        val phone = "093"
        val email = "aa"
        val address = "aaa"
        val gender = "fe"
    }

    var studentArray = ArrayList<Student>()
    fun loadData() {
        val dataString =
            resources.openRawResource(R.raw.student).bufferedReader()
                .use { it.readText() }// read the entire file as a string
        var lines = dataString.trim().split("\n") // split each line
        lines = lines.subList(1, lines.size) // get rid of the header line
        //Add to the stock Array.
        lines.forEach {
            studentArray.add(makeStudent(it))
        }
    }

    /**
     * Split one line from CSV file and create the stock object
     * @param line : one line of values from the csv file. e.g. "Industrials,3M Company,222.89,259.77,175.49"
     */
    fun makeStudent(line: String): Student {
        val cells = line.split(",")
        val student = Student(
            cells[0],
            cells[1],
            cells[2],
            cells[3],
            cells[4]
        )
        return student
    }
    lateinit var viewAdapter: RecyclerViewAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        viewManager = LinearLayoutManager(this)
        student_recycler.layoutManager = viewManager

        viewAdapter= RecyclerViewAdapter(studentArray.toTypedArray())
        student_recycler.adapter=viewAdapter
        viewAdapter.clickLambda=studentClickLambda
    }
    val studentClickLambda:(Student)->Unit={
        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra(name, it.name)
        intent.putExtra(phone, it.phone)
        intent.putExtra(email, it.email)
        intent.putExtra(address, it.address)
        intent.putExtra(gender, it.gender)
        startActivity(intent)
    }

}
