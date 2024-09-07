package com.example.shoppingbasket.models

data class Product(
    val productCode: Int,
    val productName: String,
    val price: Double,
)

data class Item(
    val productCode: Int,
    val count: Int,
)

data class BasketUpdateRequest(
    val sessionId: Long,
    val product: Product,
    val count: Int
)