package com.example.studentgradeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel: ViewModel() {
    var savedStudentList = MutableLiveData<Array<Student>>() //List of students from database
    val currentStudent = MutableLiveData<Student>()
    val database = MutableLiveData<StudentDB>()

    fun updateList(){
        val list = database.value?.studentDAO()?.getAll()
        savedStudentList.postValue(list?.toTypedArray())
    }

    fun saveCurrentStudent(){
        currentStudent.value?.let{
            database.value?.studentDAO()?.insert(it)
        }
        updateList()
    }

    fun deleteStudentByID(studentid:String){
        database.value?.studentDAO()?.delete(studentid)
        updateList()
    }

    fun deleteStudentByName(studentname:String){
        database.value?.studentDAO()?.deleteStudentByName(studentname)
        updateList()
    }

    fun updateStudentByName(){
        currentStudent.value?.let{
            database.value?.studentDAO()?.updateStudentByName(it.name, it.grade)
        }
        updateList()
    }

    fun updateStudentByID(){
        currentStudent.value?.let{
            database.value?.studentDAO()?.updateStudentByID(it.studentID, it.grade)
        }
        updateList()
    }

    init {
        savedStudentList.value = emptyArray()
    }

    fun setCurrentStudent(student:Student) {
        currentStudent.value = student
        currentStudent.postValue(student)
    }

    fun get_list(): String {
        var result = ""
        savedStudentList.value?.forEach { result += it.toString() }
        return result
    }
}