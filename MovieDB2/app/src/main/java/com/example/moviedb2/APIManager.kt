package com.example.moviedb2

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class APIManager(val movieViewModel: MovieViewModel) {
    private val apiURL = "https://api.themoviedb.org/"
    private val apiKey = "d42386619e555818ff32cfb65e4da97e"
    private val language = "en-US"

    val retrofit = Retrofit.Builder()
        .baseUrl(apiURL)
        .build()

    val service = retrofit.create(MovieService::class.java)

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

    fun decodeJson(json: String):Array<Movie> {
        val data = JSONObject(json)
        var arrayOfObject = data.getJSONArray("results")
        var movies = arrayListOf<Movie>()
        for (i in 0 until arrayOfObject.length()) {
            val movie = Movie()

            val userDetail = arrayOfObject.getJSONObject(i)
            movie.id = userDetail.getInt("id")
            movie.poster_path = userDetail.getString("poster_path")
            movie.backdrop_path = userDetail.getString("backdrop_path")
            movie.title = userDetail.getString("title")
            movie.vote_average = userDetail.getDouble("vote_average")
            movie.overview = userDetail.getString("overview")
            movie.release_date = userDetail.getString("release_date")
            movie.rating = 0.0f

            movies.add(movie)
        }
        return movies.toTypedArray()
    }

    fun fetchNowPlay() {
        val service = retrofit.create(MovieService::class.java)
        val call = service.getListNowPlay( apiKey, language)
        call.enqueue(MovieCallback())
    }

    fun fetchUpcome(){
        val service = retrofit.create(MovieService::class.java)
        val call = service.getListUpcoming( apiKey, language)
        call.enqueue(MovieCallback())
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
                   movieViewModel.addMovieList(decodeJson(it.string()))
                }
            }
        }
    }

}