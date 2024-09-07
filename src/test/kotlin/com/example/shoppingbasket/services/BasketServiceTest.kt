package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.BasketUpdateRequest
import com.example.shoppingbasket.models.Item
import com.example.shoppingbasket.models.Product
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class BasketServiceTest {
    private val sut = BasketService()
    
    private val productMilk = Product(11, "milk", 1.4)
    private val productEgg = Product(22, "eggs", 2.3)

    @Test
    fun addOneItem() {
        val actual = sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 2))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  2)).toString())
        assertEquals(actual, listOf(Item(11,2)))
    }

    @Test
    fun addTheSameItemMultipleTimes() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 2))
        val actual = sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  5)).toString())
        assertEquals(actual, listOf(Item(11,5)))
    }

    @Test
    fun addMultipleItems() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual = sut.addItemToBasket(BasketUpdateRequest(1, productEgg, 1))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3, 22 to  1)).toString())
        assertEquals(actual, listOf(Item(11,3), Item(22,1)))
    }

    @Test
    fun addToMultipleSessions() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual1 = sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 1))
        sut.addItemToBasket(BasketUpdateRequest(2, productEgg, 1))
        val actual2 = sut.addItemToBasket(BasketUpdateRequest(2, productMilk, 1))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 4),
            2 to mutableMapOf(22 to 1, 11 to 1)).toString())
        assertEquals(actual1, listOf(Item(11,4)))
        assertEquals(actual2, listOf(Item(22,1), Item(11,1)))
    }

    @Test
    fun removeOneItemUpdatesCount() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual = sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 1))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 2)).toString())
        assertEquals(actual, listOf(Item(11,2)))
    }

    @Test
    fun removeOneItemEntirely() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        sut.addItemToBasket(BasketUpdateRequest(1, productEgg, 1))
        val actual = sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 3))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(22 to 1)).toString())
        assertEquals(actual, listOf(Item(22,1)))
    }

    @Test
    fun removeLastItemRemovesBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual =  sut.removeItemFromBasket(BasketUpdateRequest(1, productMilk, 3))

        assertTrue(sut.basketMap.isEmpty())
        assertEquals(actual, emptyList<Item>())
    }

    @Test
    fun removeItemNotInBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual = sut.removeItemFromBasket(BasketUpdateRequest(1, productEgg, 3))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3)).toString())
        assertEquals(actual, listOf(Item(11,3)))
    }

    @Test
    fun removeItemFromNonExistentBasket() {
        sut.addItemToBasket(BasketUpdateRequest(1, productMilk, 3))
        val actual = sut.removeItemFromBasket(BasketUpdateRequest(2, productEgg, 3))

        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3)).toString())
        assertEquals(actual, emptyList<Item>())
    }

}