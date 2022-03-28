package com.example.studentgradeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel:StudentViewModel by viewModels<StudentViewModel>()
        viewModel.database.value = StudentDB.getDBObject(this)
        viewModel.updateList()

        viewModel.savedStudentList.observe(this) {
            record_text.text = viewModel.get_list()
        }

        viewModel.currentStudent.observe(this){
            id_text.setText(it.studentID.toString())
            name_text.setText(it.name.toString())
            grade_text.setText(it.grade.toString())
        }

        add_button.setOnClickListener {
            if (id_text.text.isNotBlank() and name_text.text.isNotBlank() and grade_text.text.isNotBlank()) {
                val id = id_text.text.toString()
                val name = name_text.text.toString()
                val grade = grade_text.text.toString().toInt()
                val student = Student()
                student.grade = grade
                student.name = name
                student.studentID = id
                viewModel.setCurrentStudent(student)
                viewModel.saveCurrentStudent()
            }
        }
        update_button.setOnClickListener {
            val id = id_text.text.toString()
            val name = name_text.text.toString()
            val grade = grade_text.text.toString().toInt()
            val student = Student()
            student.grade = grade
            student.name = name
            student.studentID = id
            viewModel.setCurrentStudent(student)

            if (grade_text.text.isNotBlank()) {
                if (id_text.text.isNotBlank()) {
                    viewModel.updateStudentByID()
                } else if (name_text.text.isNotBlank()) {
                    viewModel.updateStudentByName()
                }
            }
        }
        delete_button.setOnClickListener {
            if (id_text.text.isNotBlank()) {
                viewModel.deleteStudentByID(id_text.text.toString())
            } else if (name_text.text.isNotBlank()) {
                viewModel.deleteStudentByName(name_text.text.toString())
            }
        }
    }
}