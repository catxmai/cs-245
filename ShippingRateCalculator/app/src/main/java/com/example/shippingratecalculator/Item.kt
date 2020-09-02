package com.example.shippingratecalculator

class Item (val name:String, val weight:Double){
    var price = 0.0

    companion object{
        val ItemPrice = doubleArrayOf(5.0, 3.0, 2.0)
    }
    init {
        if (weight<=0){
            price=0.0
        } else if (weight<10){
            price=weight* ItemPrice[0]
        } else if (weight<100){
            price=weight* ItemPrice[1]
        } else {
            price=weight* ItemPrice[2]
        }
    }

    fun getInformation(index: Int): String{
        return "$index $name ($weight)lb $$price"
    }
}