package com.example.moviedb2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    val apiManager = MutableLiveData<APIManager>()
    val upcomingMovie = MutableLiveData<Array<Movie>>()
    val nowplayingMovie = MutableLiveData<Array<Movie>>()
    val currentMovieList = MutableLiveData<Array<Movie>>()
    val currentMovie = MutableLiveData<Movie>()
    val database = MutableLiveData<MovieDB>()

    fun updateList(){
        val list = database.value?.movieDAO()?.getAll()?.sortedByDescending { it.rating }
        currentMovieList.postValue(list?.toTypedArray())
    }

    init {
        apiManager.value = APIManager(this)
        upcomingMovie.value = emptyArray()
        nowplayingMovie.value = emptyArray()
        currentMovieList.value = emptyArray()
    }

    fun isInDB(movie:Movie):Boolean{
        val list = database.value?.movieDAO()?.getMovie(movie.id)
        if (list==null){
            return false
        } else if (list!!.size>=1) {
            return true
        }
        return false
    }

    fun filterList(){
        currentMovieList.value!!.forEach {
            if (isInDB(it)){
                val list = database.value?.movieDAO()?.getMovie(it.id)
                it.rating = list!!.get(0)!!.rating
            }
        }
        currentMovieList.postValue(currentMovieList.value)
    }

    fun addMovieListNP(array:Array<Movie>){
        nowplayingMovie.value = array
        nowplayingMovie.postValue(array)
    }

    fun addMovieListUC(array:Array<Movie>){
        upcomingMovie.value = array
        upcomingMovie.postValue(array)
    }

    fun addMovieList(array:Array<Movie>){
        currentMovieList.value = array
        filterList()
        currentMovieList.postValue(array)
    }

    fun getList(): Array<Movie> {
        return currentMovieList.value!!.sortedByDescending { it.rating }.toTypedArray()
    }

    fun updateMovieByID(){
        currentMovie.value?.let{
            database.value?.movieDAO()?.updateMovieByID(it.id, it.rating)
        }
        updateList()
    }

    fun saveCurrentMovie(){
        currentMovie.value?.let{
            database.value?.movieDAO()?.insert(it)
        }
        updateList()
    }

}