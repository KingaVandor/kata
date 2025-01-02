package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Hand.*
import com.example.shoppingbasket.models.Player.*
import com.example.shoppingbasket.models.Rank
import com.example.shoppingbasket.models.Rank.*
import com.example.shoppingbasket.models.Score


class Poker {

    private val cards: Map<Char, Rank> = mapOf(
        '2' to TWO,
        '3' to  THREE,
        '4' to  FOUR,
        '5' to  FIVE,
        '6' to  SIX,
        '7' to  SEVEN,
        '8' to  EIGHT,
        '9' to NINE,
        'T' to  TEN,
        'J' to  JACK,
        'Q' to  QUEEN,
        'K' to  KING,
        'A' to  ACE,
    )

    fun pokerHands(blackHand: List<String>, whiteHand: List<String>): Score {
        if (isTie(blackHand, whiteHand)) return Score(TIE)

        val whiteScore = getHighest(whiteHand).copy(player = White)
        val blackScore = getHighest(blackHand).copy(player = Black)

        if (whiteScore.hand.ordinal > blackScore.hand.ordinal) return whiteScore
        if (blackScore.hand.ordinal > whiteScore.hand.ordinal) return blackScore
        return listOf(whiteScore, blackScore).maxByOrNull { it.value!! } ?: Score(TIE)
    }

    private fun getHighest(hand: List<String>): Score {
        isFlush(hand)?.let {
            isStraight(hand)?.let {
                straightScore -> return straightScore.copy(hand = STRAIGHT_FLUSH)
            }
        }
        isFour(hand)?.let { return it }
        isFullHouse(hand)?.let { return it }
        isFlush(hand)?.let { return it }
        isStraight(hand)?.let { return it }
        isThree(hand)?.let { return it }
        isTwoPairs(hand)?.let { return it }
        isPair(hand)?.let { return it }


        val highestCard: Rank = hand.map { cards[it[0]] }.maxBy { it!!.trueValue }!!
        return Score(HIGH, value = highestCard.trueValue, rank = highestCard)
    }
    
    private fun isTie(blackHand: List<String>, whiteHand: List<String>): Boolean {
        return blackHand.map { black -> black[0] }.containsAll(whiteHand.map { white -> white[0] })
    }

    private fun isFour(hand: List<String>): Score? {
        val isFour = hand.groupingBy { it[0] }.eachCount().filter { it.value == 4 }.map { it.key }

        return if (isFour.isEmpty()) null
        else Score(hand = FOURS, rank = cards[isFour[0]], value = cards[isFour[0]]!!.trueValue)
    }

    private fun  isFullHouse(hand: List<String>): Score? {
        val hasThree = isThree(hand)
        val hasPair = isPair(hand)

        return if (hasThree != null && hasPair != null) Score(hand = FULL, rank = hasThree.rank, value = hasThree.value )
        else null
    }

    private fun isFlush(hand: List<String>): Score? {
        val isFlush = hand.groupingBy { it[1] }.eachCount().filter { it.value == 5 }
        return if (isFlush.isEmpty()) null
        else {
            val highest: Rank = hand.mapNotNull { cards[it[0]] }.maxByOrNull { it.ordinal }!!
            Score(FLUSH, value = highest.trueValue, rank = highest)
        }
    }

    private fun isThree(hand: List<String>): Score? {
        val isThree = hand.groupingBy { it[0] }.eachCount().filter { it.value == 3 }.map { it.key }
        return if (isThree.isEmpty()) null
        else Score(hand = THREES, rank = cards[isThree[0]], value = cards[isThree[0]]!!.trueValue * 3)
    }

    private fun isPair(hand: List<String>): Score? {
        val pairs = getPairs(hand)
        return if(pairs.size == 1) Score(PAIR, rank = cards[pairs.keys.first()], value = (cards[pairs.keys.first()]!!.trueValue * 2))
        else null
    }

    private fun isTwoPairs(hand: List<String>): Score? {
        val pairs = getPairs(hand)
        return if(pairs.size == 2) {
            val highest: Rank = pairs.entries.mapNotNull { cards[it.key] }.maxByOrNull { it.ordinal }!!
            Score(TWO_PAIRS, rank = highest, value = highest.trueValue * 2)
        }
        else null
    }

    private fun getPairs(hand: List<String>): Map<Char, Int> {
        return hand.groupingBy { it[0] }.eachCount().filter { it.value == 2 }
    }

    private fun isStraight(hand: List<String>): Score? {
        val sorted = hand.map { it[0] }.sortedBy { it }
        var consecutive = 0
        var previousOrdinal = 0
        for (card in sorted) {
            val currentCard: Rank = cards[card]!!
            val currentOrdinal = currentCard.ordinal
            if (currentOrdinal == previousOrdinal + 1) consecutive++
            previousOrdinal = currentOrdinal
        }

        return if(consecutive == 4) {
            val highest: Rank = hand.mapNotNull { cards[it[0]] }.maxByOrNull { it.ordinal }!!
            Score(STRAIGHT, value = highest.trueValue, rank = highest)
        } else null
    }


}












