package com.example.newsapp

import java.io.Serializable

class News(t:String, b:String, i:Int):Serializable{
    var title=t
    var body=b
    var picture=i

}