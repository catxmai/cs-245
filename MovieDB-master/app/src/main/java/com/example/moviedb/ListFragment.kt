package com.example.moviedb

import GenreDecoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONException


class ListFragment : Fragment() {
    lateinit var viewModel: MovieViewModel
    lateinit var viewAdapter: MovieAdapter
    lateinit var viewManger: RecyclerView.LayoutManager


    fun updateRecycler(){
        viewAdapter.movieData=viewModel.nowshowingMovie.value!!
        viewAdapter.notifyDataSetChanged()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)


        viewManger = LinearLayoutManager(activity)
        viewAdapter = MovieAdapter(ArrayList())

        val jsonString = resources.openRawResource(R.raw.genre).bufferedReader().use { it.readText() }
        val genre=GenreDecoder(jsonString).codes

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list_recycler.layoutManager = linearLayoutManager
        list_recycler.adapter = viewAdapter
        var name = arguments?.getString("MovieList")
            try {

                if (name == "upComing") {

                    TVType.setText("Upcoming")

                    viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer {
                        for (i in 0 until viewModel.upcomingMovie.value!!.size) {
                            viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer {
                                val genres = it.get(i).genre_ids
                                val genres1 = genres.split(",")
                                var genreString = ""
                                for (i in 0 until genres1.size) {
                                    val genrenum = genres1[i].replace("[", "")
                                        .replace("]", "")
                                        .toInt()
                                    genreString += genre.get(genrenum) + " "
                                }
                                it.get(i).genre_st = genreString
                            }
                            )
                        }
                    })


                } else {
                    TVType.setText("Now Playing")
                    viewModel.fetchNowPlay()


                    viewModel.nowshowingMovie.observe(viewLifecycleOwner, Observer {
                        for (i in 0 until viewModel.nowshowingMovie.value!!.size) {
                            viewModel.nowshowingMovie.observe(viewLifecycleOwner, Observer {
                                val genres = it.get(i).genre_ids
                                val genres1 = genres.split(",")
                                var genreString = ""
                                for (i in 0 until genres1.size) {
                                    val genrenum = genres1[i].replace("[", "")
                                        .replace("]", "")
                                        .toInt()
                                    genreString += genre.get(genrenum) + " "
                                }
                                it.get(i).genre_st = genreString
                            }
                            )
                        }
                    })
                }

            } catch (e: JSONException) {
                //exception
                e.printStackTrace()
            }

    }
    fun recyclerViewItemSelected(movie: Movie) {
        viewModel.singleMovie.value = movie
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }


}


