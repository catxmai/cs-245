package com.example.jokeapp

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class APIManager(val viewModel: JokeViewModel) {
    private val apiURL = "https://official-joke-api.appspot.com/"

    fun fetchJoke(){
        val retrofit=Retrofit.Builder()
            .baseUrl(apiURL)
            .build()
        val service = retrofit.create(JokeService::class.java)
        val call = service.getJoke()
        call.enqueue(JokeCallback())
    }

    interface JokeService{
        @GET("jokes/random")
        fun getJoke():Call<ResponseBody>
    }

    fun nextJoke(json:String){
        val joke = Joke()
        val data = JSONObject(json)
        joke.setup = data.getString("setup")
        joke.punchline = data.getString("punchline")
        viewModel.nextJoke(joke)
    }

    inner class JokeCallback: Callback<ResponseBody>{
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful){
                val body = response.body()
                if (body!=null)
                    nextJoke(body.string())
            }
        }

    }
}