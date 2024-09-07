package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.BasketItem
import com.example.shoppingbasket.models.BasketUpdateRequest
import com.example.shoppingbasket.models.Checkout
import com.example.shoppingbasket.models.Register
import com.example.shoppingbasket.models.Product
import org.springframework.stereotype.Component

@Component
class BasketService {
    val basketMap = mutableMapOf<Long, MutableMap<Int, Int>>()
    val productMap = mutableMapOf<Int, Product>()

    fun addItemToBasket(request: BasketUpdateRequest): List<Register> {
        val productCode = upsertProductInCache(request.product)

        synchronized(this) {
            val existingBasket = basketMap[request.sessionId]
            if (existingBasket.isNullOrEmpty()) basketMap[request.sessionId] = mutableMapOf(productCode to request.count)
            else {
                val newCount = existingBasket[productCode]?.plus(request.count) ?: request.count
                existingBasket[productCode] = newCount
                basketMap[request.sessionId] = existingBasket
            }

            return getBasket(request.sessionId)
        }
    }

    fun removeItemFromBasket(request: BasketUpdateRequest): List<Register> {
        val productCode = request.product.productCode

        synchronized(this) {
            val existingBasket = basketMap[request.sessionId] ?: return getBasket(request.sessionId)
            val productCountInBasket = existingBasket[productCode] ?: return getBasket(request.sessionId)

            if ((productCountInBasket - request.count) <= 0) {
                existingBasket.remove(productCode)
                // if that was the last item, remove basket entirely
                if (basketMap[request.sessionId].isNullOrEmpty()) basketMap.remove(request.sessionId)
            } else {
                existingBasket[productCode] = productCountInBasket - request.count
                basketMap[request.sessionId] = existingBasket
            }

            return getBasket(request.sessionId)
        }
    }

    fun calculateCheckout(sessionId: Long): Checkout? {
        val basket = basketMap[sessionId] ?: return null

        val allItemsInBasket = basket.entries.mapNotNull { entry -> productMap[entry.key]?.let { BasketItem(it, entry.value) } }
        val itemsToPayFor = allItemsInBasket.map { item -> BasketItem(item.product, getDiscountedCount(item.quantity)) }
        val finalPriceIncludingDiscount: Double = itemsToPayFor.map { item -> item.product.price * item.quantity }.fold(0.0) { acc, next -> acc + next }

        return Checkout(allItemsInBasket, itemsToPayFor, finalPriceIncludingDiscount)
    }

    fun getDiscountedCount(quantity: Int): Int {
        return quantity - (quantity / 2)
    }

    private fun getBasket(sessionId: Long) = basketMap[sessionId]
        ?.entries
        ?.map { Register(it.key, it.value) }
        ?.toList() ?: emptyList()

    private fun upsertProductInCache(product: Product): Int {
        synchronized(this) {
            productMap[product.productCode] = product
        }
        return product.productCode
    }
}


