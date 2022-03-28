package com.example.moviedb2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*

class RecyclerViewAdapter(var movieData: Array<Movie>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return RecyclerViewHolder(viewItem)
    }

    lateinit var clickLambda: (Movie) -> Unit

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(movieData[position], clickLambda)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(movie: Movie, clickLambda: (Movie) -> Unit) {
            PosterLoader.getInstance().loadURL(movie.backdrop_path,viewItem.findViewById<ImageView>(R.id.poster_img2))
            viewItem.findViewById<TextView>(R.id.title_text2).text = movie.title
            viewItem.ratingBar2.rating = movie.rating

            viewItem.setOnClickListener {
                clickLambda(movie)
            }
        }
    }


}