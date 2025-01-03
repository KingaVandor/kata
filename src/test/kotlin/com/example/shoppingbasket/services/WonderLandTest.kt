package com.example.shoppingbasket.services

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class WonderLandTest {
    private val sut = WonderLand()

    @Test
    fun generateWonderland() {

       val actual = sut.generateWonderland()
        println(actual)
        assertNotNull(actual)
        assertEquals(6, actual.toString().length)
        val actualDigitMap: Map<Char, Int> = actual.toString().toCharArray().toList().groupingBy { it }.eachCount()
        val doubleDigitMap: Map<Char, Int> = (actual * 2).toString().toCharArray().toList().groupingBy { it }.eachCount()
        assertEquals(doubleDigitMap, actualDigitMap)

    }
}

