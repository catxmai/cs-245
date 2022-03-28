package com.example.streetfood

import java.io.Serializable
class Restaurant(com:String, ser:String, sty:String, add:String, pho:String, pos:String,
                 comment:String, rating:Float):Serializable {
    var company = com
    var service = ser
    var style = sty
    var address = add
    var phone = pho
    var postcode = pos
    var comment = comment
    var rating = rating
}