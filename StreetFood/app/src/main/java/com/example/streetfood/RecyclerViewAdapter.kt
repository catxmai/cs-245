package com.example.streetfood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.restaurant_view.view.*


private val TextView.setImageResource: Unit
    get() {}

class RecyclerViewAdapter(var restaurantData: Array<Restaurant>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.restaurant_view,
            parent,
            false
        )
        return RecyclerViewHolder(viewItem)
    }

    lateinit var clickLambda: (Restaurant) -> Unit

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(restaurantData[position], clickLambda)
    }

    override fun getItemCount(): Int {
        return restaurantData.size
    }

    class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(restaurant: Restaurant, clickLambda: (Restaurant) -> Unit) {
            viewItem.findViewById<TextView>(R.id.name_text).text = restaurant.company
            viewItem.findViewById<TextView>(R.id.style_text).text = restaurant.style
            viewItem.findViewById<TextView>(R.id.serve_text).text = restaurant.service
            viewItem.ratingBar.rating = restaurant.rating

            viewItem.setOnClickListener {
                clickLambda(restaurant)
            }
        }
    }


}