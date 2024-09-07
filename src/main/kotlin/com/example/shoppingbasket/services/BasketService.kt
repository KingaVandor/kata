package com.example.shoppingbasket.services

import org.springframework.stereotype.Component

@Component
class BasketService  {
    val basketMap = mutableMapOf<Long, MutableMap<Int, Int>>()

    fun addItemToBasket(request: BasketUpdateRequest) {
        val productCode = request.product.productCode

        synchronized(this) {
            val existingBasket = basketMap[request.sessionId]

            if (existingBasket.isNullOrEmpty()) basketMap[request.sessionId] = mutableMapOf(productCode to request.count)
            else {
                val newCount = existingBasket[productCode]?.plus(request.count) ?: request.count
                existingBasket[productCode] = newCount
                basketMap[request.sessionId] = existingBasket
            }
        }
    }
    fun removeItemFromBasket(request: BasketUpdateRequest) {
        val productCode = request.product.productCode

        synchronized(this) {
            val existingBasket = basketMap[request.sessionId] ?: return
            val productCountInBasket = existingBasket[productCode] ?: return

            if ((productCountInBasket - request.count) <= 0) {
                existingBasket.remove(productCode)
                // if that was the last item, remove basket entirely
                if (basketMap[request.sessionId].isNullOrEmpty()) basketMap.remove(request.sessionId)
            } else {
                existingBasket[productCode] = productCountInBasket - request.count
                basketMap[request.sessionId] = existingBasket
            }
        }
    }

}

data class Product(
    val productCode: Int,
    val productName: String,
    val price: Double,
)

data class BasketUpdateRequest(
    val sessionId: Long,
    val product: Product,
    val count: Int
)
