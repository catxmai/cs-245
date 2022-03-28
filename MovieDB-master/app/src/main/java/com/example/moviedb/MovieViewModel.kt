package com.example.moviedb

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class MovieViewModel : ViewModel(), ValueEventListener{

    var genre = MutableLiveData<ArrayList<genre>>()
    var upcomingMovie = MutableLiveData<ArrayList<Movie>>()
    var nowshowingMovie = MutableLiveData<ArrayList<Movie>>()
    var singleMovie = MutableLiveData<Movie>()

    init {
        nowshowingMovie.value=ArrayList()
        upcomingMovie.value=ArrayList()
        genre.value=ArrayList()
        firebase.value = Firebase.database.getReference("")
        firebase.value?.addValueEventListener(this)
    }

    fun addMovieListNP(json: String) {
        val data = JSONObject(json)
        var arrayOfObject = data.getJSONArray("results")
        //var savedMovies = MutableLiveData<ArrayList<Movie>>()
        var movies = ArrayList<Movie>()
        for (i in 0 until arrayOfObject.length()) {
            val movie = Movie()

            // create a JSONObject for fetching single user data
            val userDetail = arrayOfObject.getJSONObject(i)
            movie.id = userDetail.getInt("id")
            movie.poster_path = userDetail.getString("poster_path")
            movie.backdrop_path = userDetail.getString("backdrop_path")
            movie.title = userDetail.getString("title")
            movie.vote_average = userDetail.getDouble("vote_average")
            movie.overview = userDetail.getString("overview")
            movie.release_date = userDetail.getString("release_date")
            movie.star = 0.0f

            movies.add(movie)

        }
        nowshowingMovie.value= movies


    }
    fun addMovieListUC(json: String) {
        val data = JSONObject(json)
        var arrayOfObject = data.getJSONArray("results")
        var movies = ArrayList<Movie>()
        //databaseUpcome?.movieDAO()?.deleteAll()

        for (i in 0 until arrayOfObject.length()) {
            val moviee = Movie()
            // create a JSONObject for fetching single user data
            val userDetail = arrayOfObject.getJSONObject(i)
            moviee.poster_path = userDetail.getString("poster_path")
            moviee.backdrop_path = userDetail.getString("backdrop_path")
            moviee.title = userDetail.getString("title")
            moviee.vote_average = userDetail.getDouble("vote_average")
            moviee.overview = userDetail.getString("overview")
            moviee.release_date = userDetail.getString("release_date")
            moviee.star = 0.0f
            moviee.id = userDetail.getInt("id")

            movies.add(moviee)
        }

        upcomingMovie.value = movies
    }



}