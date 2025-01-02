package com.example.shoppingbasket.services

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test


class ChangeTest {
    private val sut = Change()

    @Test
    fun calculateChange() {
        assertEquals(2, sut.calculateChange(5).size)
        assertEquals(4, sut.calculateChange(10).size)
        assertEquals(6, sut.calculateChange(15).size)
        assertEquals(9, sut.calculateChange(20).size)
        assertEquals(13, sut.calculateChange(25).size)
        assertEquals(49, sut.calculateChange(50).size)
        assertEquals(73, sut.calculateChange(60).size)
//        assertEquals(73, sut.calculateChange(70).size)
    }
}

