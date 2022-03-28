package com.example.stockapp2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StockViewModel : ViewModel(), ValueEventListener {

    var stockList = MutableLiveData<ArrayList<Stock>>()
    var currentStock = MutableLiveData<Stock>()
    var firebase = MutableLiveData<DatabaseReference>()

    init {
        stockList.value = ArrayList<Stock>()
        firebase.value = Firebase.database.getReference("")
        firebase.value?.addValueEventListener(this)
    }


    fun setCurrentStock(stock: Stock) {//When the user click a stock in the recycler view
        currentStock.value= stock
        currentStock.postValue(currentStock.value)
    }

    fun updateCurrentStock(share: Int) {//When the user click save button
        currentStock.value?.share = share
        firebase.value?.child(currentStock.value!!.id)?.child("share")?.setValue(share)
    }

    override fun onCancelled(error: DatabaseError) {
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        val stocks = ArrayList<Stock>()
        snapshot.children.forEach {
            Log.d("it", it.toString())
            it.getValue(Stock::class.java)?.let{
                stocks.add(it)
            }
        }
        stockList.postValue(stocks)
    }
}

