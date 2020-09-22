package com.example.stockapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var stockData: Array<Stock>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.stock_view,
            parent,
            false
        )
        return RecyclerViewHolder(viewItem)
    }

    lateinit var clickLambda: (Stock) -> Unit

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(stockData[position], clickLambda)
    }

    override fun getItemCount(): Int {
        return stockData.size
    }

    class RecyclerViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(stock: Stock, clickLambda: (Stock) -> Unit) {
            viewItem.findViewById<TextView>(R.id.name_text).text = stock.name
            viewItem.findViewById<TextView>(R.id.price_text).text = stock.price
            viewItem.setOnClickListener {
                clickLambda(stock)
            }
        }
    }


}