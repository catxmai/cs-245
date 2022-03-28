package com.example.studentgradeapp

import androidx.room.*

@Dao
interface StudentDAO {
    @Query("SELECT * FROM studentTable")
    fun getAll():List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student:Student)

    @Query("UPDATE studentTable SET grade=:studentgrade WHERE name=:studentname")
    fun updateStudentByName(studentname: String, studentgrade: Int)

    @Query("UPDATE studentTable SET grade=:studentgrade WHERE studentID=:studentid")
    fun updateStudentByID(studentid: String, studentgrade: Int)

    @Query("DELETE FROM studentTable WHERE studentID=:studentid")
    fun delete(studentid:String)

    @Query("DELETE FROM studentTable WHERE name=:studentname")
    fun deleteStudentByName(studentname:String)
}