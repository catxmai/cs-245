package com.example.streetfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    lateinit var viewManger: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    val viewModel: FoodViewModel by activityViewModels<FoodViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManger = LinearLayoutManager(activity)
        viewAdapter = RecyclerViewAdapter(viewModel.getList())

        restaurant_recyclerView.layoutManager = viewManger
        restaurant_recyclerView.adapter = viewAdapter

        val style = arguments?.getString("style_arg").toString()
        viewModel.restaurantList.observe(viewLifecycleOwner, Observer { it ->
            val tempList = it.filter { it.style==style }.toTypedArray()
            viewAdapter.restaurantData = tempList
            viewAdapter.notifyDataSetChanged()
        })


        val clickLambda:(Restaurant)->Unit={
            viewModel.updateCurrentRestaurant(restaurant=it)
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
        viewAdapter.clickLambda = clickLambda

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}