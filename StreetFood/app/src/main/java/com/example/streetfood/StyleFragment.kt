package com.example.streetfood

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_style.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StyleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StyleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    val viewModel: FoodViewModel by activityViewModels<FoodViewModel>()
    val clickLambda:(String)->Unit={
        val bundle = Bundle()
        bundle.putString("style_arg", it)
        findNavController().navigate(R.id.action_styleFragment_to_listFragment, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        american_button.setOnClickListener {
            clickLambda("American")
        }

        chinese_button.setOnClickListener {
            clickLambda("Chinese")
        }

        dessert_button.setOnClickListener {
            clickLambda("Dessert")
        }

        fastfood_button.setOnClickListener {
            clickLambda("Fast Food")
        }

        french_button.setOnClickListener {
            clickLambda("French")
        }

        italian_button.setOnClickListener {
            clickLambda("Italian")
        }

        japanese_button.setOnClickListener {
            clickLambda("Japanese")
        }

        mexican_button.setOnClickListener {
            clickLambda("Mexican")
        }

        pizza_button.setOnClickListener {
            clickLambda("Pizza")
        }

        steakhouse_button.setOnClickListener {
            clickLambda("Steakhouse")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_style, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StyleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StyleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}