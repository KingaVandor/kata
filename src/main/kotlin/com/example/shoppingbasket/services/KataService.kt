package com.example.shoppingbasket.services

import com.example.shoppingbasket.services.Month.*
import com.example.shoppingbasket.services.WeekDay.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

class KataService {
    fun multiplicationTable(size: Int): Array<IntArray> =
        (1..size).map { i ->
            (1..size).map { j ->
                i * j
            }.toIntArray()
        }.toTypedArray()

    fun digitize(n: Long): IntArray = n.toString().reversed().map { it.toString().toInt() }.toIntArray()


    fun getAscii(c: Char): Int = c.code

    fun productFib(prod: Long): LongArray {
        var first = 1L
        var second = 1L
        var i = 0

        while (i < prod) {
            if (first * second == prod) return longArrayOf(first, second, 1)
            else if (first * second > prod) return longArrayOf(first, second, 0)
            else {
                first = second.also { second += first }
                i++
            }
        }

        return longArrayOf(0)
    }

    fun rowSumOddNumbers(n: Int): Int = n * n * n

    fun lcdDigits(num: Int): String {
        val digitMap = mapOf(
            0 to listOf(" _ ", "| |", "|_|"),
            1 to listOf("   ", "  |", "  |"),
            2 to listOf(" _ ", " _|", "|_ "),
            3 to listOf(" _ ", " _|", " _|"),
            4 to listOf("   ", "|_|", "| |"),
            5 to listOf(" _ ", "|_ ", " _|"),
            6 to listOf(" _ ", "|_ ", "|_|"),
            7 to listOf(" _ ", "  |", "  |"),
            8 to listOf(" _ ", "|_|", "|_|"),
            9 to listOf(" _ ", "|_|", "| |"),

            )
        val numMap = num.toString().mapNotNull { digitMap[it.digitToInt()] }

        val topRow = numMap.map { it[0] + " " }.joinToString { it }.replace(",", "")
        val midRow = numMap.map { it[1] + " " }.joinToString { it }.replace(",", "")
        val bottomRow = numMap.map { it[2] + " " }.joinToString { it }.replace(",", "")

        return """
            $topRow
            $midRow
            $bottomRow
        """.trimIndent()
    }


    fun encode(num: Int): String {

        val romanMap = mapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I",
        )

        var answ = ""
        if (num == 0) return ""
        var remainder = num


        while (remainder > 0) {
            for (entry in romanMap) {
                if (remainder >= entry.key) {
                    answ += entry.value.repeat(remainder / entry.key)
                    remainder %= entry.key
                }
            }
        }
        return answ
    }

    fun decode(str: String): Int {
        var answ = 0
        if (str == "") return answ
        var prev = '_'

        for (c in str) {
            when (c) {
                'M' -> answ += if (prev == 'C') 800 else 1000
                'D' -> answ += if (prev == 'C') 300 else 500
                'C' -> answ += if (prev == 'X') 80 else 100
                'L' -> answ += if (prev == 'X') 30 else 50
                'X' -> answ += if (prev == 'I') 8 else 10
                'V' -> answ += if (prev == 'I') 3 else 5
                'I' -> answ += 1
            }
            prev = c
        }
        return answ
    }

    fun diamond(c: Char): String {
        if (c == 'A') return c.toString()
        val placeInAlphabet = c.lowercaseChar().code - 'a'.code + 1
        val rows: MutableList<String> = emptyList<String>().toMutableList()
        for (i in 1..placeInAlphabet) {
            val midLetter = if (i == 1) "A" else " "
            var wing = ""
            for (j in 2..placeInAlphabet) {
                wing += if (j == i) (j + 'a'.code - 1).toChar().uppercase() else " "
            }
            val row = wing.reversed() + midLetter + wing
            rows.add(row)
        }

        val middleline = rows.last()
        val outerlines = rows.dropLast(1)

        val countDown = buildString {
            for (i in outerlines) {
                append(i)
                appendLine()
            }
            append(middleline)
            appendLine()
            for (i in outerlines.reversed()) {
                append(i)
                appendLine()
            }
        }

        return countDown.dropLast(1)
    }

    fun diamondSimple(n: Int): String {

        val rows: MutableList<String> = emptyList<String>().toMutableList()
        (1..<n).map { i ->
            var row = ""
            (1..<2 * n).map { j ->
                row += if ((j < n - i + 1) || (j > n + i - 1)) " " else "*"
            }
            rows.add(row)
        }

        val countDown = buildString {
            for (i in rows) {
                append(i)
                appendLine()
            }
            append("*".repeat(2 * n - 1))
            appendLine()
            for (i in rows.reversed()) {
                append(i)
                appendLine()
            }
        }

        return countDown.dropLast(1)
    }

    fun spellOut(n: Int): String {

        if (n == 0) return "zero"
        var remainder = n
        var answ = ""
        var prev = 0
        val numMap = mapOf(
            1000000 to "million",
            1000 to "thousand",
            100 to "hundred",
            90 to "ninety",
            80 to "eighty",
            70 to "seventy",
            60 to "sixty",
            50 to "fifty",
            40 to "forty",
            30 to "thirty",
            20 to "twenty",
            12 to "twelve",
            11 to "eleven",
            10 to "ten",
            9 to "nine",
            8 to "eight",
            7 to "seven",
            6 to "six",
            5 to "five",
            4 to "four",
            3 to "three",
            2 to "two",
            1 to "one",
        )

        while (remainder > 0) {
            for (entry in numMap) {
                if (remainder >= entry.key) {
                    answ += getAnd(prev, entry.key) + getTimes(
                        remainder,
                        entry.key,
                        numMap
                    ) + numMap[entry.key] + getComma(entry.key) + " "
                    remainder %= entry.key
                    prev = entry.key
                }
            }
        }

        return answ.trim()
    }

    private fun getComma(key: Int): String {
        return if (key in listOf(1000, 1000000)) ","
        else ""
    }

    private fun getAnd(prev: Int, key: Int): String {
        return if (prev >= 100 && key < 100) "and "
        else ""

    }

    private fun getTimes(remainder: Int, key: Int, numMap: Map<Int, String>): String {
        return if (key < 100 && remainder / key == 1) return ""
        else {
            numMap[remainder / key]?.let { "$it " } ?: (spellOut(remainder / key) + " ")
        }

    }

    fun inArray(array1: Array<String>, array2: Array<String>): Array<String> {
        return array1
            .filter { iselementof(array2, it) }
            .distinct()
            .sorted()
            .toTypedArray()
    }

    private fun iselementof(array2: Array<String>, s: String): Boolean {
        return array2.filter { it.contains(s) }.toList().isNotEmpty()
    }

    fun xmas(): String {
        val gifts = listOf(
            "A partridge in a pear tree.",
            "Two turtle doves and",
            "Three french hens",
            "Four calling birds",
            "Five golden rings",
            "Six geese a-laying",
            "Seven swans a-swimming",
            "Eight maids a-milking",
            "Nine ladies dancing",
            "Ten lords a-leaping",
            "Eleven pipers piping",
            "Twelve drummers drumming",
        )
        val nums = listOf(
            "first",
            "second",
            "third",
            "fourth",
            "fifth",
            "sixth",
            "seventh",
            "eight",
            "ninth",
            "tenth",
            "eleventh",
            "twelfth",
        )

        return buildString {
            for (i in 0..11) {
                append("On the ${nums[i]} day of Christmas,")
                appendLine()
                append("My true love gave to me:")
                appendLine()
                for (j: Int in (0..i).reversed()) {
                    append(gifts[j])
                    appendLine()
                }
                appendLine()
            }
        }.dropLast(2)
    }

    private val blocks = listOf(
        Pair('B', 'O'),
        Pair('X', 'K'),
        Pair('D', 'Q'),
        Pair('C', 'P'),
        Pair('N', 'A'),
        Pair('G', 'T'),
        Pair('R', 'E'),
        Pair('T', 'G'),
        Pair('Q', 'D'),
        Pair('F', 'S'),
        Pair('J', 'W'),
        Pair('H', 'U'),
        Pair('V', 'I'),
        Pair('A', 'N'),
        Pair('O', 'B'),
        Pair('E', 'R'),
        Pair('F', 'S'),
        Pair('L', 'Y'),
        Pair('P', 'C'),
        Pair('Z', 'M'),
    )

    fun abc(s: String): Boolean {
        val blockMap = blocks.groupingBy { it }.eachCount()
        return canFindPath(s, blockMap)
    }


    private fun canFindPath(s: String, blockMap: Map<Pair<Char, Char>, Int>): Boolean {
        if (s == "") return true

        val blocksWeCanUse =
            blockMap.filter { (it.key.first == s.first() || it.key.second == s.first()) && it.value > 0 }.toList()
        if (blocksWeCanUse.isEmpty()) return false

        val paths = blocksWeCanUse.filter {
            val remainingMap = blockMap.toMutableMap()
            remainingMap[it.first] = it.second - 1

            canFindPath(s.substring(1), remainingMap)
        }

        return paths.isNotEmpty()
    }

    fun isBalanced(s: String): Boolean {
        val bracketMap = mapOf(
            '(' to ')',
            '[' to ']',
            '{' to '}',
        )
        val bracketList = listOf('(', ')', '[', ']', '{', '}')

        var onlyBrackets = s.filter { bracketList.contains(it) }
        if (onlyBrackets.length % 2 != 0) return false

        while (onlyBrackets.isNotEmpty()) {
            val originalLength = onlyBrackets.length
            var new = onlyBrackets
            for (i in 0..<originalLength) {
                if ((i + 1 < originalLength) && (bracketMap[onlyBrackets[i]] == onlyBrackets[i + 1])) {
                    new = onlyBrackets.replaceRange(IntRange(i, i + 1), "_")
                    break

                }
            }
            new = new.filter { bracketList.contains(it) }
            if (new.isEmpty()) return true
            if (new.length == originalLength) return false
            else onlyBrackets = new
        }
        return true
    }

    private val anagramList = emptyList<String>().toMutableList()
    fun anagrams(s: String): Set<String> {
        generator("", s)
        return anagramList.toSet()
    }

    private fun generator(starter: String, leftover: String): Boolean {
        if (leftover.isEmpty()) {
            anagramList.add(starter)
            return true
        }

        if (leftover.length == 1) {
            anagramList.add(starter + leftover)
            return true
        }

        for (i in leftover.indices) {
            val newStarter = starter + leftover[i].toString()
            val newLeftover = leftover.replaceRange(IntRange(i, i), "")
            generator(newStarter, newLeftover)
        }
        return false

    }

    fun stats(arr: Array<Int>): Array<Double> {
        val min: Double = arr.minOf { it }.toDouble()
        val max: Double = arr.maxOf { it }.toDouble()
        val size: Double = arr.size.toDouble()
        val average: Double = BigDecimal(arr.sum().div(size)).setScale(5, RoundingMode.HALF_EVEN).toDouble()

        return arrayOf(min, max, size, average)
    }

    fun closestToZero(arr: Array<Int>): Int {
        val max = arr.maxOf { abs(it) }
        var closestToZero = max

        for (i in arr) {
            if (abs(i) < closestToZero) {
                closestToZero = i
            } else if ((abs(i) == abs(closestToZero)) && (i > 0)) closestToZero = i
        }

        return closestToZero
    }

    inline fun <reified T : Enum<T>> T.next(): T {
        val values = enumValues<T>()
        val nextOrdinal = (ordinal + 1) % values.size
        return values[nextOrdinal]
    }


    fun friday13(): WeekDay {
        var prev = SUNDAY
        val thirteens = emptyMap<WeekDay, Int>().toMutableMap()
        val months = mapOf(
            JANUARY to 31,
            FEBRUARY to 28,
            MARCH to 31,
            APRIL to 30,
            MAY to 31,
            JUNE to 30,
            JULY to 31,
            AUGUST to 31,
            SEPT to 30,
            OCT to 31,
            NOV to 30,
            DEC to 31
        )

        for (i in 1973..2024) {
            for (month in months) {
                val days: Int = if ((i % 4 == 0) && month.key == FEBRUARY) 29
                else month.value

                for (date in 1..days) {
                    val day = prev.next()
                    prev = day
                    if (date == 13) {
                        thirteens.merge(day, 1, Int::plus)
                    }
                }
            }

        }
        println("thirteens: $thirteens")
        return thirteens.maxBy { (key, value) -> value }.key
    }

    fun leapYear(year: Int): Boolean {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)
    }


    fun primeFactors(num: Int): List<Int> {
        var remainder = num
        val answer = emptyList<Int>().toMutableList()

        while (remainder > 1) {
            for (i in 2..remainder) {
                if (remainder % i == 0) {
                    answer.add(i)
                    remainder /= i
                    break
                }
            }
        }
        return answer
    }

    var finalState = emptyMap<Int, Door>().toMutableMap()

    fun hundredDoors(doorNum: Int): Door? {
        if (finalState.isEmpty()) calculateDoors()
        return finalState[doorNum]
    }

    private fun calculateDoors() {
        val doorMap = emptyMap<Int, Int>().toMutableMap()
        for (round in 1..100) {
            for (doorNumber in 1..100) {
                if (doorNumber % round == 0) {
                    doorMap.merge(doorNumber, 1, Int::plus)
                }
            }
        }

        finalState = doorMap.map { entry ->
            val openClosed = if (entry.value % 2 == 0) Door.CLOSED else Door.OPEN
            Pair(entry.key, openClosed)
        }.toMap().toMutableMap()
    }

    fun isbn(s: String): Boolean {
        val numsOnly = s.replace(" ", "").replace("-", "")
        if ((numsOnly.length != 13 && numsOnly.length != 10) || numsOnly.toLongOrNull() == null) return false

        val calcedFinalDigit = if (numsOnly.length == 13) getIsbn13Final(numsOnly) else getIsb10Final(numsOnly)
        return calcedFinalDigit == numsOnly.last().toString()
    }

    private fun getIsbn13Final(s: String): String {
        var final = 0
        for (i in 0..11) {
            val multiplier = if (i % 2 == 0) 1 else 3
            final += s[i].toString().toInt() * multiplier
        }

        return ((10 - (final % 10)) % 10).toString()
    }

    private fun getIsb10Final(s: String): String {
        var final = 0
        var times = 10
        for (i in 0..8) {
            final += s[i].toString().toInt() * times
            times -= 1
        }
        val mod = final % 11

        return when (mod) {
            0 -> "0"
            10 -> "X"
            else -> (11 - mod).toString()
        }
    }


    fun mineField(s: String): String {
        if (s.length <= 1) {
            return when (s) {
                "", "*" -> s
                else -> "0"
            }
        }

        val field = s.replace(".", "0").split("\n").toMutableList()
        val rows = field.size
        val cols = field[0].length

        for (row in 0..<rows) {
            for (col in 0..<cols) {
                if (field[row][col] == '*') {
                    for (r in -1..1) {
                        for (c in -1..1) {
                            if ((row + r in 0..<rows) && (col + c in 0..<cols) && !(c == 0 && r == 0)) {
                                val currentVal = field[r + row][c + col]
                                if (currentVal == '*') continue
                                field[row + r] = field[row + r].replaceRange(
                                    c + col,
                                    c + col + 1,
                                    (currentVal.toString().toInt() + 1).toString()
                                )
                            }
                        }
                    }
                }
            }
        }

        return buildString {
            for (row in field) {
                append(row)
                appendLine()
            }
        }.dropLast(1)
    }


//    fun pokerHands(blackHand: List<String>, whiteHand: List<String>): String {
//        val black = Pair(Player.Black, blackHand.map { it.dropLast(1) }.map { getRank(it) }.sortedDescending())
//        val white = Pair(Player.White, whiteHand.map { it.dropLast(1) }.map { getRank(it) }.sortedDescending())
//
//        val winner = getWinner(black, white) ?: return "Tie"
//
//        return "${winner.player.name} wins - ${winner.hand.longName}: ${winner.card}"
//    }
//
//    private fun getRank(card: String): Rank {
//        return Rank.entries.firstNotNullOf {
//            if (it.shortName == card) it
//            else null
//        }
//    }
//
//    private fun getWinner(black: Pair<Player, List<Rank>>, white: Pair<Player, List<Rank>>): Score? {
//        if (black.second.containsAll(white.second)) return null
//        val backScores = getScores(black)
//        for (i in 0..4) {
//            if (black.second[i].ordinal > white.second[i].ordinal) return Score(
//                Player.Black,
//                Hands.HIGH,
//                black.second[i].longName,
//                null
//            )
//            if (black.second[i].ordinal < white.second[i].ordinal) return Score(
//                Player.White,
//                Hands.HIGH,
//                white.second[i].longName,
//                null
//            )
//        }
//        return null
//    }
//
//    private fun getScores(hand: Pair<Player, List<Rank>>): Any {
//        val sames = hand.second.groupingBy { it }.eachCount()
//
//        val fours = sames.filter { it.value >= 4 }
//        if (fours.isNotEmpty()) return Score(
//            player = hand.first,
//            hand = Hands.FOUR,
//            card = fours.map { entry -> entry.key.longName }.first(),
//            fours.map { entry -> (entry.key.value) * (entry.value) }.first()
//        )
//
//        val threes = sames.filter { it.value >= 3 }
//        if (threes.isNotEmpty()) return Score(
//            player = hand.first,
//            hand = Hands.THREE,
//            card = threes.map { entry -> entry.key.longName }.first(),
//            threes.map { entry -> (entry.key.value) * (entry.value) }.first()
//        )
//
//        val pairs = sames.filter { it.value >= 2 }
////        if(pairs.isNotEmpty()) {
////            if (pairs.size == 2)
////                val highest =
////                return Score(player = hand.first, hand =  Hands.TWO_PAIRS, card =  pairs.map { entry -> entry.key.longName }.first(),  pairs.map { entry -> (entry.key.value) * (entry.value) }.first())
////        }
//        return Score(hand.first, Hands.HIGH, hand.second[0].longName, null)
//    }
}

//data class Score(
//    val player: Player,
//    val hand: Hands,
//    val card: String,
//    val value: Int?,
//)

//enum class Hands(val longName: String) {
//    TIE("Tie"),
//    STRAIGHT_FLUSH("Straight Flush"),
//    FOUR("Four of a Kind"),
//    FULL("Full House"),
//    FLUSH("Flush"),
//    STRAIGHT("Straight"),
//    THREE("Three of a Kind"),
//    TWO_PAIRS("Two Pairs"),
//    PAIR("Pair"),
//    HIGH("High Card"),
//}
//
//enum class Player() {
//    Black,
//    White
//}
//
//enum class Rank(val value: Int, val longName: String, val shortName: String) {
//    TWO(2, "2", "2"),
//    THREE(3, "3", "3"),
//    FOUR(4, "4", "4"),
//    FIVE(5, "5", "5"),
//    SIX(6, "6", "6"),
//    SEVEN(7, "7", "7"),
//    EIGHT(8, "8", "8"),
//    NINE(9, "9", "9"),
//    TEN(10, "Ten", "T"),
//    JACK(10, "Jack", "J"),
//    QUEEN(10, "Queen", "Q"),
//    KING(10, "King", "K"),
//    ACE(10, "Ace", "A"),
//}
//
enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPT, OCT, NOV, DEC
}

enum class Door {
    CLOSED, OPEN,
}











