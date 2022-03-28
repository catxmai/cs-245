package com.example.moviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(var movieData: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return RecyclerViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // set the data in items of the RecyclerView
        holder.MVname.text = movieData[position].title
        holder.MVGenre.text = movieData[position].genre_st
        PosterLoader.getInstance().loadURL(movieData[position].poster_path, holder.IV_style)
        holder.bind(movieData[position])
    }


    override fun getItemCount(): Int {
        //return the item count
        return movieData.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //connecting od with the text views
        internal var MVname: TextView = itemView.findViewById(R.id.MVName) as TextView
        internal var MVGenre: TextView = itemView.findViewById(R.id.MVGenre) as TextView
        internal var IV_style: ImageView = itemView.findViewById(R.id.IV_style) as ImageView
        fun bind(movie: Movie) {
        }
    }
}