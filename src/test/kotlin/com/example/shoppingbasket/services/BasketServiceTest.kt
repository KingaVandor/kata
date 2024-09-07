package com.example.shoppingbasket.services

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class BasketServiceTest {
    private val sut = BasketService()

    @Test
    fun addOneItem() {
        sut.addItem(1, Product(11, "milk", 1.4), 2)
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  2)).toString())
    }

    @Test
    fun addTheSameItemMultipleTimes() {
        sut.addItem(1, Product(11, "milk", 1.4), 2)
        sut.addItem(1, Product(11, "milk", 1.4), 3)
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to  5)).toString())
    }

    @Test
    fun addMultipleItems() {
        sut.addItem(1, Product(11, "milk", 1.4), 3)
        sut.addItem(1, Product(22, "eggs", 2.3), 1)
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 3, 22 to  1)).toString())
    }

    @Test
    fun addToMultipleSessions() {
        sut.addItem(1, Product(11, "milk", 1.4), 3)
        sut.addItem(1, Product(11, "milk", 1.4), 1)
        sut.addItem(2, Product(22, "eggs", 2.3), 1)
        sut.addItem(2, Product(11, "milk", 1.4), 1)
        assertEquals(sut.basketMap.toString(), mutableMapOf(1 to mutableMapOf(11 to 4),
            2 to mutableMapOf(22 to 1, 11 to 1)).toString())
    }

}