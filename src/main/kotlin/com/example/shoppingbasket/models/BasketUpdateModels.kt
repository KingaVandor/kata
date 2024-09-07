package com.example.shoppingbasket.models

data class Product(
    val productCode: Int,
    val productName: String,
    val price: Double,
)

data class Register(
    val productCode: Int,
    val count: Int,
)

data class BasketUpdateRequest(
    val sessionId: Long,
    val product: Product,
    val count: Int
)

data class BasketItem(
    val product: Product,
    val quantity: Int,
)

data class Checkout(
    val allItemsInBasket: List<BasketItem>,
    val itemsToPayFor: List<BasketItem>,
    val finalPriceIncludingDiscount: Double
)