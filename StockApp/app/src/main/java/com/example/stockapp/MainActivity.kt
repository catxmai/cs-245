package com.example.stockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val currentStock=stockArray.filter {
            it.sector==sector_spinner.selectedItem.toString()
        }
        viewAdapter.stockData=currentStock.toTypedArray()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    var stockArray = ArrayList<Stock>()

    /**
     * read the CSV data
     * */
    fun loadData() {
        val dataString =
                resources.openRawResource(R.raw.sp500).bufferedReader()
                        .use { it.readText() }// read the entire file as a string
        var lines = dataString.trim().split("\n") // split each line
        lines = lines.subList(1, lines.size) // get rid of the header line
        //Add to the stock Array.
        lines.forEach {
            stockArray.add(makeStock(it))
        }
    }

    /**
     * Split one line from CSV file and create the stock object
     * @param line : one line of values from the csv file. e.g. "Industrials,3M Company,222.89,259.77,175.49"
     */
    fun makeStock(line: String): Stock {
        val cells = line.split(",")
        val stock = Stock(
                cells[0],
                cells[1],
                cells[2],
                cells[3],
                cells[4]
        )
        return stock
    }

    lateinit var viewAdapter: RecyclerViewAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
        val sectors = stockArray.map {
            it.sector
        }
        //sectors:["Industrials","Industrials", "Health Care", "Health Care", ...]
        val sectorArray = sectors.distinct().toTypedArray()
        //sectorArray:["Industrials", "Health Care", ...]
        val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                sectorArray
        )
        sector_spinner.adapter = adapter
        sector_spinner.onItemSelectedListener = this

        viewManager = LinearLayoutManager(this)
        stock_recycler.layoutManager = viewManager

        viewAdapter= RecyclerViewAdapter(stockArray.toTypedArray())
        stock_recycler.adapter=viewAdapter
        viewAdapter.clickLambda=stockClickLambda
    }
    val stockClickLambda:(Stock)->Unit={
        val toast=Toast.makeText(this, "${it.name}, " +
                "52 wk low: {${it.low}" +
                "52 wk high: {${it.high}", Toast.LENGTH_LONG)
        toast.show()
    }
}