package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Hand
import com.example.shoppingbasket.models.Player.*
import com.example.shoppingbasket.models.Rank.*
import com.example.shoppingbasket.models.Score
import kotlin.test.Test
import kotlin.test.assertEquals


class PokerTest {
    private val sut = Poker()

    @Test
    fun pokerHands() {

        assertEquals(
            Score(Hand.TIE),
            sut.pokerHands(
                blackHand = listOf("2H", "3D", "5S", "9C", "KD"),
                whiteHand = listOf("2D", "3H", "5C", "9S", "KH")
            )
        )
        assertEquals(
            Score(Hand.STRAIGHT_FLUSH, rank = EIGHT, value = 8, player = Black),
            sut.pokerHands(
                blackHand = listOf("4H", "5H", "6H", "7H", "8H"),
                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
            )
        )
        assertEquals(
            Score(Hand.STRAIGHT_FLUSH, rank = NINE, value = 9, player = White),
            sut.pokerHands(
                blackHand = listOf("4H", "5H", "6H", "7H", "8H"),
                whiteHand = listOf("5H", "6H", "7H", "8H", "9H"),
            )
        )
        assertEquals(
            Score(Hand.FOURS, rank = TWO, value = 2, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "2D", "2S", "2C", "5D"),
                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
            )
        )
        assertEquals(
            Score(Hand.FULL, rank = TWO, value = 6, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "2D", "2S", "5C", "5D"),
                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
            )
        )
        assertEquals(
            Score(Hand.FLUSH, rank = KING, value = 13, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "3H", "6H", "QH", "KH"),
                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
            )
        )
        assertEquals(
            Score(Hand.STRAIGHT, rank = EIGHT, value = 8, player = Black),
            sut.pokerHands(
                blackHand = listOf("4H", "5D", "6S", "7C", "8D"),
                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
            )
        )
        assertEquals(
            Score(Hand.THREES, rank = TWO, value = 6, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "2D", "2S", "9C", "KD"),
                whiteHand = listOf("2C", "3H", "4S", "5C", "KH")
            )
        )
        assertEquals(
            Score(Hand.TWO_PAIRS, rank = THREE, value = 6, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "2D", "3S", "3C", "KD"),
                whiteHand = listOf("2C", "3H", "4S", "5C", "KH")
            )
        )

        assertEquals(
            Score(Hand.PAIR, rank = TWO, value = 4, player = Black),
            sut.pokerHands(
                blackHand = listOf("2H", "2D", "3S", "5C", "KD"),
                whiteHand = listOf("2C", "3H", "4S", "5C", "KH")
            )
        )

        assertEquals(
            Score(Hand.HIGH, rank = KING, value = 13, player = White),
            sut.pokerHands(
                blackHand = listOf("2H", "3D", "6S", "8C", "QD"),
                whiteHand = listOf("2C", "3H", "4S", "5C", "KH")
            )
        )
    }
}

