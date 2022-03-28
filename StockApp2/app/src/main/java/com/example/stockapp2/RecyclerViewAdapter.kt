package com.example.stockapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var stockList: ArrayList<Stock>, var clickLambda: (Stock) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(stockList[position], clickLambda)
    }

    class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(stock: Stock, clickLambda: (Stock) -> Unit) {
            view.findViewById<TextView>(R.id.item_name_text).text = stock.stock
            view.findViewById<TextView>(R.id.item_price_text).text = "$" + stock.price.toString()
            view.findViewById<TextView>(R.id.item_share_text).text = stock.share.toString()
            view.setOnClickListener {
                clickLambda(stock)
            }
        }
    }
}