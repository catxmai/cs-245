package com.example.moviedb2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewManger: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    val viewModel: MovieViewModel by activityViewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManger = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = RecyclerViewAdapter(viewModel.getList())

        movie_recyclerView.layoutManager = viewManger
        movie_recyclerView.adapter = viewAdapter

        viewModel.database.value = MovieDB.getDBObject(context!!)
        viewModel.apiManager.value?.fetchUpcome()
        viewAdapter.movieData = viewModel.getList()
        viewAdapter.notifyDataSetChanged()

//        viewModel.currentMovieList.observe(viewLifecycleOwner, Observer { it ->
//
//        })
        navigation.setOnNavigationItemSelectedListener {
            if (it.itemId==R.id.action_nowplaying){
                viewModel.apiManager.value?.fetchNowPlay()
                viewAdapter.movieData = viewModel.getList().sortedByDescending { it.rating }.toTypedArray()
                viewAdapter.notifyDataSetChanged()

            }
            else if (it.itemId==R.id.action_upcoming){
                viewModel.apiManager.value?.fetchUpcome()
                viewAdapter.movieData = viewModel.getList().sortedByDescending { it.rating }.toTypedArray()
                viewAdapter.notifyDataSetChanged()
            }
            return@setOnNavigationItemSelectedListener true
        }
//        nowplaying_button.setOnClickListener {
//            viewModel.apiManager.value?.fetchNowPlay()
//            viewAdapter.movieData = viewModel.getList().sortedByDescending{it.rating}.toTypedArray()
//            viewAdapter.notifyDataSetChanged()
//        }
//
//        upcoming_button.setOnClickListener {
//            viewModel.apiManager.value?.fetchUpcome()
//            viewAdapter.movieData = viewModel.getList().sortedByDescending{it.rating}.toTypedArray()
//            viewAdapter.notifyDataSetChanged()
//        }
        val clickLambda:(Movie)->Unit={
            viewModel.currentMovie.value = it
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
        viewAdapter.clickLambda = clickLambda

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}