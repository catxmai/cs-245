package com.example.studentgradeapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studentTable")
class Student {
    @PrimaryKey
    var studentID: String = "S001"

    var name: String = "No Name"
    var grade: Int = 0

    override fun toString(): String {
        return "$studentID | $name | $grade \n"
    }
}