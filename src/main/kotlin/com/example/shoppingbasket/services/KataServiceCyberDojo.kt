package com.example.shoppingbasket.services

import com.example.shoppingbasket.models.Direction
import com.example.shoppingbasket.models.Door
import com.example.shoppingbasket.models.Month.APRIL
import com.example.shoppingbasket.models.Month.AUGUST
import com.example.shoppingbasket.models.Month.DEC
import com.example.shoppingbasket.models.Month.FEBRUARY
import com.example.shoppingbasket.models.Month.JANUARY
import com.example.shoppingbasket.models.Month.JULY
import com.example.shoppingbasket.models.Month.JUNE
import com.example.shoppingbasket.models.Month.MARCH
import com.example.shoppingbasket.models.Month.MAY
import com.example.shoppingbasket.models.Month.NOV
import com.example.shoppingbasket.models.Month.OCT
import com.example.shoppingbasket.models.Month.SEPT
import com.example.shoppingbasket.models.Neighbours
import com.example.shoppingbasket.models.TennisPlayer
import com.example.shoppingbasket.models.TennisPlayer.A
import com.example.shoppingbasket.models.TennisPlayer.B
import com.example.shoppingbasket.models.TennisScore
import com.example.shoppingbasket.models.WeekDay
import com.example.shoppingbasket.models.WeekDay.SUNDAY
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

class KataServiceCyberDojo {
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

    fun encodeToRoman(num: Int): String {
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

    fun decodeFromRoman(str: String): Int {
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

    fun spellOutNumber(n: Int): String {

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
                    answ += getAnd(prev, entry.key) +
                            getTimes(remainder, entry.key, numMap) +
                            numMap[entry.key] +
                            getComma(entry.key) +
                            " "
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
        return if (key < 100 && remainder / key == 1) ""
        else {
            numMap[remainder / key]?.let { "$it " } ?: (spellOutNumber(remainder / key) + " ")
        }
    }

    fun inArray(array1: Array<String>, array2: Array<String>): Array<String> {
        return array1
            .filter { isElementOf(array2, it) }
            .distinct()
            .sorted()
            .toTypedArray()
    }

    private fun isElementOf(array2: Array<String>, s: String): Boolean {
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
    private val blockMap = blocks.groupingBy { it }.eachCount()
    fun abc(s: String): Boolean {
        return canFindPath(s, blockMap)
    }

    private fun canFindPath(s: String, currentBlockMap: Map<Pair<Char, Char>, Int>): Boolean {
        if (s == "") return true

        val blocksWeCanUse: List<Pair<Pair<Char, Char>, Int>> =
            currentBlockMap.filter { (it.key.first == s.first() || it.key.second == s.first()) && it.value > 0 }
                .toList()
        if (blocksWeCanUse.isEmpty()) return false

        val paths = blocksWeCanUse.filter {
            val remainingMap = currentBlockMap.toMutableMap()
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
            var new = onlyBrackets
            for (i in onlyBrackets.indices) {
                if ((i + 1 < onlyBrackets.length) && (bracketMap[onlyBrackets[i]] == onlyBrackets[i + 1])) {
                    new = onlyBrackets.replaceRange(IntRange(i, i + 1), "_")
                    break
                }
            }
            new = new.filter { bracketList.contains(it) }
            if (new.isEmpty()) return true
            else if (new.length == onlyBrackets.length) return false
            else onlyBrackets = new
        }
        return true
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
            if ((abs(i) < closestToZero) || (abs(i) == abs(closestToZero)) && (i > 0)) closestToZero = i
        }

        return closestToZero
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
                val numberOfDays: Int = if ((i % 4 == 0) && month.key == FEBRUARY) 29
                else month.value

                for (date in 1..numberOfDays) {
                    val day = prev.next()
                    prev = day
                    if (date == 13) {
                        thirteens.merge(day, 1, Int::plus)
                    }
                }
            }

        }
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

    private var finalState = emptyMap<Int, Door>().toMutableMap()

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
                    for (neighbour in Neighbours.entries) {
                        if ((row + neighbour.row in 0..<rows) &&
                            (col + neighbour.col in 0..<cols)
                        ) {

                            val neighbourVal = field[neighbour.row + row][neighbour.col + col]
                            if (neighbourVal == '*') continue
                            field[row + neighbour.row] = field[row + neighbour.row].replaceRange(
                                neighbour.col + col,
                                neighbour.col + col + 1,
                                (neighbourVal.toString().toInt() + 1).toString()
                            )
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

    fun wordWrapper(s: String, max: Int): String {
        val wordsList = s.split(" ")
        val builder: StringBuilder = StringBuilder(s.length * 10)
        var current = ""

        for (i in wordsList.indices) {
            if ((current.length + wordsList[i].length) > max) {
                builder.append(current.trim())
                builder.appendLine()
                current = "${wordsList[i]} "

                if (i == wordsList.size - 1) {
                    builder.append(current.trim())
                }
            } else {
                current += "${wordsList[i]} "
                if (i == wordsList.size - 1) {
                    builder.append(current.trim())
                }
            }
        }
        return builder.toString()
    }

    fun unSplice(s: String): String {
        return s.replace("\\\n", "")
    }

    fun tinyMaze(s: String): String {
        val maze: List<String> = s.replace("[", "").replace("]", "").replace(":", "").replace(" ", "").split("\n")
        val path = getPath(Pair(0, 0), maze).toList()

        return if (path.isEmpty()) "no way out"
        else buildString {
            path.forEach {
                append(it)
                appendLine()
            }
        }.dropLast(1)
    }

    private fun getPath(start: Pair<Int, Int>, maze: List<String>): MutableList<String> {
        var path = emptyList<String>().toMutableList()
        val newMaze = maze.toMutableList()

        for (direction in Direction.entries) {
            if (path.isNotEmpty()) break
            val nextRow = start.first + direction.row
            val nextCol = start.second + direction.col

            path = when (getNextIfInRange(Pair(nextRow, nextCol), maze)) {
                '0' -> {
                    val newRow: String = maze[nextRow].replaceRange(nextCol, nextCol + 1, 'x'.toString())
                    newMaze[nextRow] = newRow
                    getPath(Pair(nextRow, nextCol), newMaze)
                }

                'E' -> maze.toMutableList()
                else -> emptyList<String>().toMutableList()
            }
        }
        return path
    }

    private fun getNextIfInRange(next: Pair<Int, Int>, maze: List<String>): Char? {
        val rowNum = maze.size
        val colNum = maze[0].length
        return if ((next.first >= rowNum || next.second >= colNum)
            || (next.first < 0 || next.second < 0)
        ) {
            null
        } else maze[next.first][next.second]
    }

    fun saddlePoints(arr: Array<Array<Int>>): List<Int> {
        val rowMaxes = getRowMaxes(arr)
        val colMins = getColMins(arr, rowMaxes.maxOf { it.value })

        return getSaddlePoints(arr, rowMaxes, colMins)

    }

    private fun getColMins(arr: Array<Array<Int>>, max: Int): Map<Int, Int> {
        val colMins = emptyMap<Int, Int>().toMutableMap()
        for (col in 0..<arr[0].size) {
            var colMin = max
            for (row in arr.indices) {
                if (arr[row][col] < colMin) colMin = arr[row][col]
            }
            colMins[col] = colMin
        }
        return colMins.toMap()
    }

    private fun getRowMaxes(arr: Array<Array<Int>>): Map<Int, Int> {
        val rowMaxes = emptyMap<Int, Int>().toMutableMap()
        arr.toList().mapIndexed { index, list -> rowMaxes[index] = list.max() }
        return rowMaxes.toMap()
    }

    private fun getSaddlePoints(arr: Array<Array<Int>>, rowMaxes: Map<Int, Int>, colMins: Map<Int, Int>): List<Int> {
        val saddlePoints = emptyList<Int>().toMutableList()
        for (row in arr.indices) {
            for (col in 0..<arr[0].size) {
                val currentVal = arr[row][col]
                if (currentVal >= (rowMaxes[row] ?: 0) && currentVal <= (colMins[col] ?: 0)
                ) {
                    saddlePoints.add(arr[row][col])
                }
            }
        }
        return saddlePoints
    }

    fun tennis(scores: List<TennisPlayer>): Pair<TennisScore, TennisPlayer?> {
        if (scores.isEmpty()) return Pair(TennisScore.LOVE, null)
        val scoreMap: Map<TennisPlayer, Int> = scores.groupingBy { it }.eachCount()

        if (scoreMap[A] == scoreMap[B]) {
            return when (scoreMap[A]) {
                1 -> Pair(TennisScore.FIFTEEN, null)
                2 -> Pair(TennisScore.THIRTY, null)
                3 -> Pair(TennisScore.FORTY, null)
                else -> Pair(TennisScore.DEUCE, null)
            }
        }

        val currentWinner: TennisPlayer = scoreMap.maxBy { it.value }.key
        return when (scoreMap[currentWinner]) {
            1 -> Pair(TennisScore.FIFTEEN, currentWinner)
            2 -> Pair(TennisScore.THIRTY, currentWinner)
            3 -> Pair(TennisScore.FORTY, currentWinner)
            4 -> Pair(TennisScore.ADVANTAGE, currentWinner)
            else -> Pair(TennisScore.GAME, currentWinner)
        }


    }

    fun <T> removeDuplicates(list: List<T>): List<T> =
        list.distinct()

    private var cacheList: MutableList<String> = emptyList<String>().toMutableList()

    fun recentlyUsedList(s: String, max: Int): List<String> {
        if (s.isEmpty()) return cacheList
        if (!cacheList.contains(s)) {
            cacheList.add(0, s)
            if (cacheList.size > max) cacheList =
                cacheList.dropLast(cacheList.size - max).toMutableList()
        } else {
            cacheList = cacheList.filter { it != s }.toMutableList()
            cacheList.add(0, s)
        }
        return cacheList
    }

    fun reorder(s: String, range: Pair<Int, Int>, position: Int): String {
        if (range.first > range.second || range.first < 0 || position < 0 || range.first >= s.length || range.second + 1 >= s.length || position >= s.length) return "out of range"

        val start = s.substring(0, minOf(range.first, position))
        val end = s.substring(maxOf(range.second + 1, position), s.length)
        val toMove = s.substring(range.first, range.second + 1)
        val mid = if (range.second < position) s.substring(range.second + 1, position)
        else s.substring(position, range.first)

        return if (range.second < position) start + mid + toMove + end
        else start + toMove + mid + end
    }

    fun arePhoneNumbersConsistent(numbers: List<String>): Boolean {
        for (i in numbers.indices) {
            for (j in numbers.indices) {
                if (i == j) continue
                if (numbers[i].contains(numbers[j].toRegex())) return false
            }

        }
        return true
    }

    fun numberChain(num: Int): Pair<List<Int>, Int> {
        val numberList = emptyList<Int>().toMutableList()
        var current = num

        while (numberList.groupingBy { it }.eachCount().values.none { it > 1 }) {
            val ascending = current.toString().toCharArray().sorted().joinToString(separator = "").toInt()
            val descending = current.toString().toCharArray().sortedDescending().joinToString(separator = "").toInt()

            numberList.add(descending - ascending)
            current = descending - ascending
        }

        return Pair(numberList, numberList.size)
    }

    fun magicSquare(): String {
        val numbersList = listOf(1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0)
        val rows = getRows(emptyList(), numbersList).toSet()
            .filter { rowsAddUp(it) && colsAddUp(it) && diagonalsAddUp(it) }

        return buildString { rows.forEach { append(it).appendLine() } }.dropLast(1)
    }

    private fun diagonalsAddUp(row: List<Double>): Boolean {
        val first = row[0] + row[4] + row[8]
        val second = row[2] + row[4] + row[6]
        return first == second
    }

    private fun colsAddUp(row: List<Double>): Boolean {
        val first = row[0] + row[3] + row[6]
        val second = row[1] + row[4] + row[7]
        val third = row[2] + row[5] + row[8]
        return first == second && second == third
    }

    private fun rowsAddUp(row: List<Double>): Boolean {
        val first = row[0] + row[1] + row[2]
        val second = row[3] + row[4] + row[5]
        val third = row[6] + row[7] + row[8]
        return first == second && second == third
    }

    private fun getRows(starter: List<Double>, remainingNums: List<Double>): List<List<Double>> {
        val answer = emptyList<List<Double>>().toMutableList()
        if (starter.size == 9) {
            answer.add((starter))
        } else if (starter.size < 9) {
            for (i in remainingNums.indices) {
                val newStarter = starter.toMutableList()
                newStarter.add(remainingNums[i])
                val leftover = remainingNums.toMutableList()
                leftover.removeAt(i)

                answer.addAll(getRows(newStarter, leftover))
            }
        }

        return answer

    }

    fun longestPrefix(strings: List<String?>, min: Int = strings.size): Int {
        if (min == strings.size) {
            val shortestString = strings.filterNotNull().associateWith { it.length }.toSortedMap().firstKey()
            return longestPrefix(shortestString, strings, strings.size)
        }
        return strings.filterNotNull().associateWith { longestPrefix(it, strings, min) }.minOf { it.value }
    }

    private fun longestPrefix(s: String, strings: List<String?>, min: Int): Int {
        for (i: Int in s.indices) {
            if (canFindInAtLeast(s.dropLast(i), strings, min)) return s.dropLast(i).length
        }
        return 0
    }

    private fun canFindInAtLeast(sub: String, strings: List<String?>, min: Int): Boolean {
        val matches = strings.filterNotNull().filter { it.startsWith(sub) }.toList()
        return matches.size >= min
    }

    fun largestCombinedNumber(nums: List<Int>): String {
        return generateCombos("", nums).toSet().maxOfOrNull { it.toInt() }.toString()
    }

    private fun generateCombos(starter: String, leftoverNums: List<Int>): List<String> {
        if (leftoverNums.isEmpty()) return listOf(starter)
        val combos = mutableListOf<String>()
        for (num in leftoverNums) {
            val newStarter = starter + num
            val newLeftover = leftoverNums.toMutableList()
            newLeftover.remove(num)
            combos.addAll(generateCombos(newStarter, newLeftover))
        }
        return combos
    }

    fun generateRandom(min: Int, max: Int): Int {
        return min.until(max).random()
    }

    fun shuffleArray(arr: IntArray): IntArray {
        val list = arr.toMutableList()
        val size = arr.size
        for (i in arr.indices) {
            val j = generateRandom(i, size)
            val listI = list[i]
            val listJ = list[j]
            list[i] = listJ
            list[j] = listI
        }

        return list.toIntArray()
    }

    fun shuffleArrayFisherYates(arr: IntArray): IntArray {
        return arr.toList().shuffled().toIntArray()
    }

    fun levenshtein(str1: String, str2: String): Int {
        return calcLevenshtein(str1, str2, str1.length, str2.length)
    }

    private fun calcLevenshtein(str1: String, str2: String, len1: Int, len2: Int): Int {
        return if (len1 == 0) len2
        else if (len2 == 0) return len1

        else if (str1[len1-1] == str2[len2-1]) return calcLevenshtein(str1, str2, len1-1, len2-1)

        else 1 + minOf(
            calcLevenshtein(str1, str2, len1, len2-1),
            minOf(
                calcLevenshtein(str1, str2, len1-1, len2),
                calcLevenshtein(str1, str2, len1-1, len2-1)
            )
        )
    }


    fun fizzbuzz(num: Int): String {
        return if (num % 5 == 0 && num % 3 == 0) "FizzBuzz"
        else if (num % 5 == 0) "Buzz"
        else if (num % 3 == 0) "Fizz"
        else ""
    }

    fun fizzBuzzPlus(num: Int): String {
        return if (isFizzPlus(num) && isBuzzPlus(num)) "FizzBuzz"
        else if (isBuzzPlus(num)) "Buzz"
        else if (isFizzPlus(num)) "Fizz"
        else ""
    }

    private fun isFizzPlus(num: Int): Boolean {
        return num % 3 == 0 || num.toString().contains("3".toRegex())
    }

    private fun isBuzzPlus(num: Int): Boolean {
        return num % 5 == 0 || num.toString().contains("5".toRegex())
    }

}












