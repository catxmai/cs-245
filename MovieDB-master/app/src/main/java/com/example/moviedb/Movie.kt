package com.example.moviedb


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieDB")
class Movie {
    @PrimaryKey
    var id = 0
    var poster_path = ""
    var backdrop_path = ""
    var title = ""
    var vote_average = 0.0
    var overview = ""
    var release_date = ""
    var star = 0.0f

}



