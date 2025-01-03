package com.example.shoppingbasket.services


class WonderLand {
    fun generateWonderland(): Int? {
        var current = 100000

        while (current < 1000000) {
            if (isMagic(current)) return current
            current++
        }

        return null
    }

    private fun isMagic(current: Int): Boolean {
        val currentDigitMap: Map<Char, Int> = current.mapDigits()
        val doubleDigitMap: Map<Char, Int> = (current * 2).mapDigits()
        val threeTimesDigitMap: Map<Char, Int> = (current * 3).mapDigits()
        val fourTimesDigitMap: Map<Char, Int> = (current * 4).mapDigits()
        val fiveTimesDigitMap: Map<Char, Int> = (current * 5).mapDigits()
        val sixTimesDigitMap: Map<Char, Int> = (current * 6).mapDigits()

        return currentDigitMap == doubleDigitMap &&
                currentDigitMap == threeTimesDigitMap &&
                currentDigitMap == fourTimesDigitMap &&
                currentDigitMap == fiveTimesDigitMap &&
                currentDigitMap == sixTimesDigitMap

    }

    private fun Int.mapDigits(): Map<Char, Int> {
        return this.toString().toCharArray().toList().groupingBy { it }.eachCount()
    }
}