package com.example.streetfood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel:ViewModel() {
    var currentRestaurant=MutableLiveData<Restaurant>()
    var restaurantList=MutableLiveData<ArrayList<Restaurant>>()

    init{
        restaurantList.value = ArrayList()
    }

    fun updateCurrentRestaurant(restaurant: Restaurant){
        currentRestaurant.value = restaurant
    }

    fun getList(): Array<Restaurant> {
        return restaurantList.value!!.toTypedArray()
    }

    fun filterList(style:String):Array<Restaurant>{
        return  restaurantList.value!!.filter{
            it.style==style
        }.toTypedArray()
    }

}