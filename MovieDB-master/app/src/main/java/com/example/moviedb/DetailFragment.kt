package com.example.moviedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_detail.*



/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rating.setIsIndicator(true)
        var idMovie = 0

        TV_personal.isVisible = false
        rating.isVisible = false
        bt_edit.isVisible = false
        movieViewModel.singleMovie.observe(viewLifecycleOwner, Observer {
            TV_name.setText(it.title)
            TV_data.setText("Launch Date: " + it.release_date)
            TV_rate.setText("Public Rating: " + it.vote_average.toString())
            TV_type.setText("Genres: " + it.genre_st)
            TV_des.setText(it.overview)
            idMovie = it.id
            PosterLoader.getInstance().loadURL(it.backdrop_path, FV_movie)
        })
        idMovie = movieViewModel.singleMovie.value!!.id
        var idmm = 0
        println(movieViewModel.savedMovies.value?.joinToString(",") { it.title })

        if (movieViewModel.checkSaved()!!) {
            var loc = 0
            for (i in 0 until movieViewModel.savedMovies.value!!.size) {
                movieViewModel.savedMovies.observe(viewLifecycleOwner, Observer {
                    idmm = it.get(i).id
                    if (idMovie == idmm) {
                        TV_personal.setText(it.get(i)?.comments)
                        rating.setRating(it.get(i)?.star)
                        TV_personal.isVisible = true
                        rating.isVisible = true
                        bt_edit.isVisible = true
                    }
                })

            }

        }



    }
}

