package com.example.moviedb

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class APIManager(val weatherViewModel: WeatherViewModel) { //2
    private val apiURL = "https://api.themoviedb.org/"
    private val apiKey = "d42386619e555818ff32cfb65e4da97e"
    private val language = "en-US"

    val retrofit = Retrofit.Builder()
        .baseUrl(apiURL)
        .build()

    val service = retrofit.create(WeatherService::class.java)

    interface MovieService {
        @GET("3/movie/now_playing?")
        fun getListNowPlay(
            @Query("api_key") api_key: String,
            @Query("language") language: String
        ): Call<ResponseBody>

        @GET("3/movie/upcoming?")
        fun getListUpcoming(
            @Query("api_key") api_key: String,
            @Query("language") language: String
        ): Call<ResponseBody>
    }

    fun decodeJson(json: String) {
        val codes = HashMap<Int, String>()

        if (codes.size == 0) {
            val data = JSONObject(json)
            val dataArray = data.getJSONArray("genres")
            val result = ArrayList<GenreDecoder>()
            for (i in 0 until dataArray.length()) {
                val dataObject = dataArray.getJSONObject(i)
                codes[dataObject.getInt("id")] = dataObject.getString("name")
            }
        }

    }

    fun fetchNowPlay() {
        val service = retrofit.create(MovieService::class.java)
        val call = service.getListNowPlay( apiKey, language)
        call.enqueue(MovieCallback(1))
    }

    fun fetchUpcome(){
        val service = retrofit.create(MovieService::class.java)
        val call = service.getListUpcoming( apiKey, language)
        call.enqueue(MovieCallback(2))
    }

    inner class MovieCallback() :
        Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        }

        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    decodeJson(it.string())
                }
            }
        }
    }

}