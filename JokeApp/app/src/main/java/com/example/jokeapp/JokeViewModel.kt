package com.example.jokeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JokeViewModel: ViewModel(){
    val api = MutableLiveData<APIManager>()
    val currentJoke = MutableLiveData<Joke>()

    init {
        api.value = APIManager(this)
    }

    fun nextJoke(joke:Joke){
        currentJoke.value = joke
        currentJoke.postValue(currentJoke.value)
    }

}