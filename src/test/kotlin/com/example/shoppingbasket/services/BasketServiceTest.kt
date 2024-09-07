package com.example.shoppingbasket.services

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class BasketServiceTest {
    private val sut = BasketService()
    
    private val productMilk = Product(11, "milk", 1.4)
    private val productEgg = Product(22, "eggs", 2.3)

    @Test
    fun addOneItem() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 2))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  2)).toString())
    }

    @Test
    fun addTheSameItemMultipleTimes() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 2))
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  5)).toString())
    }

    @Test
    fun addMultipleItems() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.addItemToBasket(BasketUpdateRequest(1, productEgg, 1))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3, 22 to  1)).toString())
    }

    @Test
    fun addToMultipleSessions() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 1))
        sut.addItemToBasket(BasketUpdateRequest(2, productEgg, 1))
        sut.addItemToBasket(BasketUpdateRequest(2, productMilk, 1))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 4),
            2 to mutableMapOf(22 to 1, 11 to 1)).toString())
    }

    @Test
    fun removeOneItemUpdatesCount() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 1))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 2)).toString())
    }

    @Test
    fun removeOneItemEntirely() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.addItemToBasket(BasketUpdateRequest(1, productEgg, 1))
        sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 3))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(22 to 1)).toString())
    }

    @Test
    fun removeLastItemRemovesBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 3))
        assertTrue(sut.basketMap.isEmpty())
    }

    @Test
    fun removeItemNotInBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.removeItemFromBasket(BasketUpdateRequest(1, productEgg, 3))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3)).toString())
    }

    @Test
    fun removeItemFromNonExistentBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.removeItemFromBasket(BasketUpdateRequest(2, productEgg, 3))
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3)).toString())
    }

}