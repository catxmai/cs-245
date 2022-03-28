package com.example.moviedb2

import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movieDB")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movieDB WHERE id = :id")
    fun getMovie(id: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("UPDATE movieDB SET rating=:userrating WHERE id=:movieid")
    fun updateMovieByID(movieid: Int, userrating: Float)

    @Query("DELETE FROM movieDB WHERE id = :id")
    fun deleteMovie(id: String)

    @Query("DELETE FROM movieDB")
    fun deleteAll()

    @Query("SELECT * FROM movieDB LIMIT :pageSize OFFSET :pageIndex")
    fun getMoviePage(pageSize: Int, pageIndex: Int): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: Movie)

}