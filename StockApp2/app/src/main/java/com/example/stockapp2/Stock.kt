package com.example.stockapp2

data class Stock(
    val id: String = "0123",
    val stock: String = "ABC Stock",
    val price: Double = 0.5,
    var share: Int = 0,
)