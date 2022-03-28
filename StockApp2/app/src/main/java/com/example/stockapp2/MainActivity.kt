package com.example.stockapp2


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewManager: LinearLayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    val viewModel: StockViewModel by viewModels<StockViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickLambda:(Stock)->Unit={
            viewModel.setCurrentStock(it)
        }
        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerViewAdapter(ArrayList(), clickLambda)

        recycler_view?.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.stockList.observe(this) {
            viewAdapter.stockList = it
            viewAdapter.notifyDataSetChanged()
        }

        viewModel.currentStock.observe(this){
            stock_name.text = it.stock
            share_text.setText(it.share.toString())
        }
        save_button.setOnClickListener {
            viewModel.updateCurrentStock(share_text.text.toString().toInt())
        }
    }
}

