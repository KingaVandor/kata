package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Door.CLOSED
import com.example.shoppingbasket.models.Door.OPEN
import com.example.shoppingbasket.models.TennisPlayer.A
import com.example.shoppingbasket.models.TennisPlayer.B
import com.example.shoppingbasket.models.TennisScore.ADVANTAGE
import com.example.shoppingbasket.models.TennisScore.DEUCE
import com.example.shoppingbasket.models.TennisScore.FIFTEEN
import com.example.shoppingbasket.models.TennisScore.FORTY
import com.example.shoppingbasket.models.TennisScore.GAME
import com.example.shoppingbasket.models.TennisScore.LOVE
import com.example.shoppingbasket.models.TennisScore.THIRTY
import com.example.shoppingbasket.models.WeekDay
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue


class KataServiceCodeWarsTest {

    private val margin = 0.001
    private val sut = KataServiceCodeWars()

    @Test
    fun regularCases() {
        assertEquals(3.571, sut.catchTheBus("4:53 PM" to "5:00 PM", "4:47 PM" to "4:55 PM"), margin)
        assertEquals(18.75, sut.catchTheBus("7:58 AM" to "8:02 AM", "7:55 AM" to "8:01 AM"), margin)
        assertEquals(42.013, sut.catchTheBus("12:00 PM" to "12:12 PM", "11:59 AM" to "12:11 PM"), margin)
        assertEquals(48.148, sut.catchTheBus("11:35 AM" to "12:02 PM", "11:45 AM" to "11:51 AM"), margin)
        assertEquals(62.5, sut.catchTheBus("6:22 PM" to "6:23 PM", "6:21 PM" to "6:25 PM"), margin)
        assertEquals(91.667, sut.catchTheBus("12:58 PM" to "1:02 PM", "1:00 PM" to "1:06 PM"), margin)
    }

    @Test
    fun edgeCases() {
        assertEquals(0.0, sut.catchTheBus("4:53 PM" to "5:00 PM", "4:47 PM" to "4:52 PM"), margin)
        assertEquals(100.0, sut.catchTheBus("9:22 AM" to "9:23 AM", "9:24 AM" to "9:44 AM"), margin)
    }
}

