package com.example.reservation

class Reservation(val name:String, val phone_num:String,
                  val people_num:Int, val isOutside:Boolean)   {
    var people_count = 0

    init{
        people_count=people_num
    }

    fun getRecord(index:Int):String{
        return "${index+1} $name, $phone_num, $people_num people, Outside: $isOutside "
    }

}