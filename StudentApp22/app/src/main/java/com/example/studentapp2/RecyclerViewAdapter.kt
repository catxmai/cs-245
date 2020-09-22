package com.example.stockapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp2.R
import com.example.studentapp2.Student

class RecyclerViewAdapter(var studentData: Array<Student>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.student_view,
            parent,
            false
        )
        return RecyclerViewHolder(viewItem)
    }

    lateinit var clickLambda: (Student) -> Unit

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(studentData[position], clickLambda)
    }

    override fun getItemCount(): Int {
        return studentData.size
    }

    class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(student: Student, clickLambda: (Student) -> Unit) {
            viewItem.findViewById<TextView>(R.id.name_text).text = student.name
            viewItem.findViewById<TextView>(R.id.phone_text).text = student.phone
            viewItem.findViewById<ImageView>(R.id.profile_image).setImageResource(
                when{
                student.gender=="Female"->R.drawable.female
                else-> R.drawable.male
            })
            viewItem.setOnClickListener {
                clickLambda(student)
            }
        }
    }


}