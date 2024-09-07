package com.example.shoppingbasket.services

import org.springframework.stereotype.Component

@Component
class BasketService  {
    val basketMap = mutableMapOf<Long, MutableMap<Int, Int>>()

    fun addItem(sessionId: Long, product: Product, count: Int) {
        val productCode = product.productCode

        synchronized(this) {
            val existingBasket = basketMap[sessionId]

            if (existingBasket.isNullOrEmpty()) basketMap[sessionId] = mutableMapOf(productCode to count)
            else {
                val newCount = existingBasket[productCode]?.plus(count) ?: count
                existingBasket[productCode] = newCount
                basketMap[sessionId] = existingBasket
            }
        }
    }

}

data class Product(
    val productCode: Int,
    val productName: String,
    val price: Double,
)
