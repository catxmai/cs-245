package com.example.burgerapp

import java.io.Serializable

class Food(t:String, n:String, c:String, i:String, d:String, s:Boolean):Serializable{
    var type= t
    var name= n
    var calorie= c
    var imageId= i
    var description= d
    var isSelected=s

}