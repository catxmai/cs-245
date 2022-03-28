package com.example.studentgradeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDB:RoomDatabase() {
    abstract fun studentDAO(): StudentDAO

    companion object {
        private var INSTANT: StudentDB?=null

        fun getDBObject(context: Context): StudentDB?{
            if (INSTANT ==null){
                synchronized(StudentDB::class.java){
                    INSTANT = Room.databaseBuilder(context, StudentDB::class.java, "studentDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANT
        }
    }

}