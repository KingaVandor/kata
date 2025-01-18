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


class KataServiceTest {
    private val sut = KataService()

    @Test
    fun multiplicationTable() {
        val actual = sut.multiplicationTable(3)

        val expected = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(2, 4, 6),
            intArrayOf(3, 6, 9)
        )
        assertTrue(
            actual.contentDeepEquals(expected),
            "Expected:\n${expected.contentDeepToString()}\nGot:\n${actual.contentDeepToString()}"
        )
    }

    @Test
    fun digitize() {
        assertArrayEquals(intArrayOf(1, 3, 2, 5, 3), sut.digitize(35231))
        assertArrayEquals(intArrayOf(0), sut.digitize(0))
    }

    @Test
    fun getAscii() {
        assertEquals(65, sut.getAscii('A'))
        assertEquals(32, sut.getAscii(' '))
        assertEquals(33, sut.getAscii('!'))
    }

    @Test
    fun productFib() {
        assertArrayEquals(longArrayOf(55, 89, 1), sut.productFib(4895))
        assertArrayEquals(longArrayOf(1346269, 2178309, 1), sut.productFib(2932589879121))
        assertArrayEquals(longArrayOf(1, 1, 1), sut.productFib(1))
    }

    @Test
    fun rowSumOddNumbers() {
        assertEquals(1, sut.rowSumOddNumbers(1))
        assertEquals(8, sut.rowSumOddNumbers(2))
        assertEquals(2197, sut.rowSumOddNumbers(13))
        assertEquals(6859, sut.rowSumOddNumbers(19))
        assertEquals(68921, sut.rowSumOddNumbers(41))
        assertEquals(74088, sut.rowSumOddNumbers(42))
        assertEquals(405224, sut.rowSumOddNumbers(74))
        assertEquals(636056, sut.rowSumOddNumbers(86))
        assertEquals(804357, sut.rowSumOddNumbers(93))
        assertEquals(1030301, sut.rowSumOddNumbers(101))
    }

    @Test
    fun lcdDigits() {
        val expected1 = """      
 _    _    _  
 _|  | |  |_  
|_   |_|   _| 
""".trimIndent()
        assertEquals(expected1, sut.lcdDigits(205))
    }

    @Test
    fun encodeToRoman() {
        assertEquals("", sut.encodeToRoman(0))
        assertEquals("I", sut.encodeToRoman(1))
        assertEquals("XXI", sut.encodeToRoman(21))
        assertEquals("MMVIII", sut.encodeToRoman(2008))
        assertEquals("MDCLXVI", sut.encodeToRoman(1666))
    }

    @Test
    fun decodeFromRoman() {
        assertEquals(0, sut.decodeFromRoman(""))
        assertEquals(1, sut.decodeFromRoman("I"))
        assertEquals(21, sut.decodeFromRoman("XXI"))
        assertEquals(2008, sut.decodeFromRoman("MMVIII"))
        assertEquals(1666, sut.decodeFromRoman("MDCLXVI"))
        assertEquals(96, sut.decodeFromRoman("XCVI"))
        assertEquals(4, sut.decodeFromRoman("IV"))
        assertEquals(916, sut.decodeFromRoman("CMXVI"))
    }

    @Test
    fun diamond() {
        assertEquals("A", sut.diamond('A'))
        assertEquals(
            """
 A 
B B
 A 
""".trimIndent(), sut.diamond('B')
        )
        assertEquals(
            """      
  A  
 B B 
C   C
 B B 
  A  
""".trimIndent(), sut.diamond('C')
        )

    }

    @Test
    fun diamondSimple() {
        assertEquals("*", sut.diamondSimple(1))
        assertEquals(
            """
 * 
***
 * 
""".trimIndent(), sut.diamondSimple(2)
        )
        assertEquals(
            """      
  *  
 *** 
*****
 *** 
  *  
""".trimIndent(), sut.diamondSimple(3)
        )

    }


    @Test
    fun spellOutNumber() {
        assertEquals("zero", sut.spellOutNumber(0))
        assertEquals("ninety nine", sut.spellOutNumber(99))
        assertEquals("three hundred", sut.spellOutNumber(300))
        assertEquals("three hundred and ten", sut.spellOutNumber(310))
        assertEquals("one thousand, five hundred and one", sut.spellOutNumber(1501))
        assertEquals("five hundred and twelve thousand, six hundred and seven", sut.spellOutNumber(512607))
        assertEquals(
            "forty three million, one hundred and twelve thousand, six hundred and three",
            sut.spellOutNumber(43112603)
        )

    }

    @Test
    fun inArray() {
        val a2 = arrayOf("lively", "alive", "harp", "sharp", "armstrong")
        assertArrayEquals(arrayOf("live", "strong"), sut.inArray(arrayOf("xyz", "live", "strong"), a2))
        assertArrayEquals(arrayOf("arp", "live", "strong"), sut.inArray(arrayOf("live", "strong", "arp"), a2))
        assertArrayEquals(arrayOf<String>(), sut.inArray(arrayOf("tarp", "mice", "bull"), a2))
    }

    @Test
    fun xmas() {
        val expected = """
            On the first day of Christmas,
            My true love gave to me:
            A partridge in a pear tree.

            On the second day of Christmas,
            My true love gave to me:
            Two turtle doves and
            A partridge in a pear tree.

            On the third day of Christmas,
            My true love gave to me:
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the fourth day of Christmas,
            My true love gave to me:
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the fifth day of Christmas,
            My true love gave to me:
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the sixth day of Christmas,
            My true love gave to me:
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the seventh day of Christmas,
            My true love gave to me:
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the eight day of Christmas,
            My true love gave to me:
            Eight maids a-milking
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the ninth day of Christmas,
            My true love gave to me:
            Nine ladies dancing
            Eight maids a-milking
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the tenth day of Christmas,
            My true love gave to me:
            Ten lords a-leaping
            Nine ladies dancing
            Eight maids a-milking
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the eleventh day of Christmas,
            My true love gave to me:
            Eleven pipers piping
            Ten lords a-leaping
            Nine ladies dancing
            Eight maids a-milking
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.

            On the twelfth day of Christmas,
            My true love gave to me:
            Twelve drummers drumming
            Eleven pipers piping
            Ten lords a-leaping
            Nine ladies dancing
            Eight maids a-milking
            Seven swans a-swimming
            Six geese a-laying
            Five golden rings
            Four calling birds
            Three french hens
            Two turtle doves and
            A partridge in a pear tree.
        """.trimIndent()
        assertEquals(expected, sut.xmas())
    }

    @Test
    fun letterBlocks() {
        assertEquals(true, sut.abc("A"))
        assertEquals(true, sut.abc("BARK"))
        assertEquals(false, sut.abc("BOOK"))
        assertEquals(true, sut.abc("TREAT"))
        assertEquals(false, sut.abc("COMMON"))
        assertEquals(true, sut.abc("SQUAD"))
        assertEquals(true, sut.abc("CONFUSE"))
    }

    @Test
    fun isBalanced() {
        assertEquals(true, sut.isBalanced("()"))
        assertEquals(false, sut.isBalanced("()("))
        assertEquals(false, sut.isBalanced(")("))
        assertEquals(false, sut.isBalanced("{{)(}}"))
        assertEquals(false, sut.isBalanced("({)}"))
        assertEquals(true, sut.isBalanced("[({})]"))
        assertEquals(true, sut.isBalanced("{}([])"))
        assertEquals(true, sut.isBalanced("{()}[[{}]]"))
    }


    @Test
    fun stats() {
        assertEquals(
            arrayOf(-2.0, 92.0, 6.0, 21.83333).contentToString(),
            sut.stats(arrayOf(6, 9, 15, -2, 92, 11)).contentToString()
        )

    }

    @Test
    fun closestToZero() {
        assertEquals(-2, sut.closestToZero(arrayOf(6, 9, 15, -2, 92, 11)))
        assertEquals(2, sut.closestToZero(arrayOf(6, 9, 15, -2, 2, 92, 11)))
        assertEquals(2, sut.closestToZero(arrayOf(6, 9, 15, 2, -2, 92, 11)))

    }

    @Test
    fun leapYear() {
        assertTrue(sut.leapYear(2024))
        assertFalse(sut.leapYear(2023))
        assertTrue(sut.leapYear(2000))
        assertFalse(sut.leapYear(1900))

    }

    @Test
    fun friday13() {
        assertEquals(WeekDay.TUESDAY, sut.friday13())
    }

    @Test
    fun primeFactors() {
        assertEquals(listOf(2), sut.primeFactors(2))
        assertEquals(listOf(3), sut.primeFactors(3))
        assertEquals(listOf(2, 2), sut.primeFactors(4))
        assertEquals(listOf(3, 3), sut.primeFactors(9))
        assertEquals(listOf(2, 2, 3), sut.primeFactors(12))
        assertEquals(listOf(3, 5), sut.primeFactors(15))
    }

    @Test
    fun hundredDoors() {
        assertEquals(CLOSED, sut.hundredDoors(2))
        assertEquals(CLOSED, sut.hundredDoors(3))
        assertEquals(OPEN, sut.hundredDoors(4))
        assertEquals(OPEN, sut.hundredDoors(9))
        assertEquals(CLOSED, sut.hundredDoors(12))
        assertEquals(CLOSED, sut.hundredDoors(15))
        assertEquals(OPEN, sut.hundredDoors(16))
        assertEquals(OPEN, sut.hundredDoors(25))
        assertEquals(OPEN, sut.hundredDoors(36))
        assertEquals(CLOSED, sut.hundredDoors(42))
        assertEquals(OPEN, sut.hundredDoors(49))
        assertEquals(OPEN, sut.hundredDoors(64))
        assertEquals(OPEN, sut.hundredDoors(81))
        assertEquals(CLOSED, sut.hundredDoors(99))
        assertEquals(OPEN, sut.hundredDoors(100))
    }

    @Test
    fun isbn() {
        assertTrue(sut.isbn("9780470059029"))
        assertTrue(sut.isbn("97-80470-059 02 9"))
        assertTrue(sut.isbn("978 0 471 48648 0"))
        assertTrue(sut.isbn("978-0596809485"))
        assertTrue(sut.isbn("978-0-13-149505-0"))
        assertTrue(sut.isbn("978-0-262-13472-9"))

        assertTrue(sut.isbn("0471958697"))
        assertTrue(sut.isbn("0 471 60695 2"))
        assertTrue(sut.isbn("0-470-84525-2"))
        assertTrue(sut.isbn("0-321-14653-0"))

        assertFalse(sut.isbn("978047005902"))
        assertFalse(sut.isbn("9780470059x02"))
    }

    @Test
    fun mineField() {
        assertEquals(
            """
*21
12*
""".trimIndent(), sut.mineField(
                """
*..
..*
""".trimIndent()
            )
        )

        assertEquals(
            """
*211
12*1
0111
""".trimIndent(), sut.mineField(
                """
                    *...
                    ..*.
                    ....
""".trimIndent()
            )
        )
    }

    @Test
    fun wordWrapper() {
        assertEquals(
            """
                      The function
                      returns the
                      string
        """.trimIndent(), sut.wordWrapper("The function returns the string", 15)
        )
        assertEquals(
            """
                      The function returns
                      the string
        """.trimIndent(), sut.wordWrapper("The function returns the string", 20)
        )
        assertEquals(
            """
                      The function returns the string
        """.trimIndent(), sut.wordWrapper("The function returns the string", 40)
        )
    }

    @Test
    fun unSplice() {
        assertEquals("abcdef", sut.unSplice("abcdef"))
        assertEquals("abcdef", sut.unSplice("abc\\\ndef"))
        assertEquals("abcdef", sut.unSplice("ab\\\ncd\\\nef"))
        assertEquals("abc\n\\def", sut.unSplice("abc\n\\def"))
        assertEquals("abc\\def", sut.unSplice("abc\\def"))
        assertEquals("abc\ndef", sut.unSplice("abc\ndef"))
    }

    @Test
    fun tinyMaze() {
        assertEquals(
            """
            Sx1
            1xE
""".trimIndent(), sut.tinyMaze(
                """
    [[:S 0 1]
    [1 0 :E]]
""".trimIndent()
            )
        )

        assertEquals(
            "no way out", sut.tinyMaze(
                """
    [[:S 0 1]
    [[1 1 1]
    [1 0 :E]]
""".trimIndent()
            )
        )

        assertEquals(
            """
            Sx1
            1x1
            1xE
""".trimIndent(), sut.tinyMaze(
                """
    [[:S 0 1]
    [[1 0 1]
    [1 0 :E]]
""".trimIndent()
            )
        )
    }

    @Test
    fun saddlePoints() {
        assertEquals(listOf(2), sut.saddlePoints(arrayOf(arrayOf(1, 2), arrayOf(3, 4))))
        assertEquals(
            listOf(5), sut.saddlePoints(
                arrayOf(
                    arrayOf(1, 2, 3, 4, 5),
                    arrayOf(3, 4, 5, 6, 7),
                    arrayOf(5, 6, 7, 8, 9),
                    arrayOf(7, 8, 9, 10, 11),
                    arrayOf(9, 10, 11, 12, 13),
                )
            )
        )
        assertEquals(
            emptyList<Int>(), sut.saddlePoints(
                arrayOf(
                    arrayOf(1, 2, 3, 4, 5),
                    arrayOf(3, 23, 5, 6, 7),
                    arrayOf(5, 6, 11, 8, 9),
                    arrayOf(7, 8, 9, 2, 1),
                    arrayOf(9, 2, 11, 12, 0),
                )
            )
        )
    }

    @Test
    fun tennis() {
        assertEquals(Pair(LOVE, null), sut.tennis(emptyList()))

        assertEquals(Pair(FIFTEEN, null), sut.tennis(listOf(A, B)))
        assertEquals(Pair(THIRTY, null), sut.tennis(listOf(A, B, A, B)))
        assertEquals(Pair(FORTY, null), sut.tennis(listOf(A, B, A, B, A, B)))
        assertEquals(Pair(DEUCE, null), sut.tennis(listOf(A, B, A, B, A, B, A, B)))

        assertEquals(Pair(FIFTEEN, A), sut.tennis(listOf(A)))
        assertEquals(Pair(THIRTY, A), sut.tennis(listOf(A, A, B)))
        assertEquals(Pair(FORTY, B), sut.tennis(listOf(A, B, A, B, B)))
        assertEquals(Pair(ADVANTAGE, A), sut.tennis(listOf(A, B, A, B, A, B, A)))
        assertEquals(Pair(GAME, B), sut.tennis(listOf(A, B, A, B, A, B, A, B, B)))
    }

    @Test
    fun removeDuplicates() {
        assertEquals(emptyList<String>(), sut.removeDuplicates(emptyList<String>()))
        assertEquals(listOf(1, 2, 3), sut.removeDuplicates(listOf(1, 1, 2, 2, 3, 3)))
        assertEquals(listOf("a", "b"), sut.removeDuplicates(listOf("a", "a", "a", "b")))

    }

    @Test
    fun recentlyUsedList() {
        assertEquals(emptyList<String>(), sut.recentlyUsedList("", 2))
        assertEquals(listOf("cat"), sut.recentlyUsedList("cat", 2))
        assertEquals(listOf("dog", "cat"), sut.recentlyUsedList("dog", 2))
        assertEquals(listOf("hamster", "dog"), sut.recentlyUsedList("hamster", 2))
        assertEquals(listOf("dog", "hamster"), sut.recentlyUsedList("dog", 2))
    }

    @Test
    fun reorder() {
        assertEquals("out of range", sut.reorder("Hello World", Pair(-1, 2), 5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(1, -2), 5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(1, 2), -5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(11, 2), 5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(1, 11), 5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(10, 2), 5))
        assertEquals("out of range", sut.reorder("Hello World", Pair(1, 2), 11))

        assertEquals("Hloel_World", sut.reorder("Hello_World", Pair(1, 2), 5))
        assertEquals("HelloWor_ld", sut.reorder("Hello_World", Pair(6, 8), 5))
    }

    @Test
    fun arePhoneNumbersConsistent() {
        assertTrue(sut.arePhoneNumbersConsistent(listOf("07722056874", "07775656874")))
        assertFalse(sut.arePhoneNumbersConsistent(listOf("079", "07975656874")))
    }

    @Test
    fun numberChain() {
        assertEquals(Pair(listOf(0, 0), 2), sut.numberChain(444))
        assertEquals(Pair(listOf(297, 693, 594, 495, 495), 5), sut.numberChain(523))
        assertEquals(Pair(listOf(3087, 8352, 6174, 6174), 4), sut.numberChain(1234))
        assertEquals(Pair(listOf(864197532, 864197532), 2), sut.numberChain(123456789))
    }

    @Test
    fun magicSquare() {
        assertEquals(
            """
            [1.5, 4.0, 3.5, 5.0, 3.0, 1.0, 2.5, 2.0, 4.5]
            [1.5, 5.0, 2.5, 4.0, 3.0, 2.0, 3.5, 1.0, 4.5]
            [2.5, 2.0, 4.5, 5.0, 3.0, 1.0, 1.5, 4.0, 3.5]
            [2.5, 5.0, 1.5, 2.0, 3.0, 4.0, 4.5, 1.0, 3.5]
            [3.5, 1.0, 4.5, 4.0, 3.0, 2.0, 1.5, 5.0, 2.5]
            [3.5, 4.0, 1.5, 1.0, 3.0, 5.0, 4.5, 2.0, 2.5]
            [4.5, 1.0, 3.5, 2.0, 3.0, 4.0, 2.5, 5.0, 1.5]
            [4.5, 2.0, 2.5, 1.0, 3.0, 5.0, 3.5, 4.0, 1.5]
        """.trimIndent(), sut.magicSquare()
        )
    }

    @Test
    fun longestPrefix() {
        assertEquals(0, sut.longestPrefix(listOf("a", "b")))
        assertEquals(1, sut.longestPrefix(listOf("abcde", "abc", "a", "ab", "abcd")))
        assertEquals(2, sut.longestPrefix(listOf("new", "next")))
        assertEquals(3, sut.longestPrefix(listOf("newest", "new", "newly")))
        assertEquals(0, sut.longestPrefix(listOf("pond", "pod", "new", "newest")))
        assertEquals(0, sut.longestPrefix(listOf("pond", "new", "newly"), 2))
        assertEquals(2, sut.longestPrefix(listOf("pond", "pod", "new", "newest"), 2))
    }


    @Test
    fun largestCombinedNumber() {
        assertEquals("95021", sut.largestCombinedNumber(listOf(50, 2, 1, 9)))
        assertEquals("56550", sut.largestCombinedNumber(listOf(5, 50, 56)))
        assertEquals("42423420", sut.largestCombinedNumber(listOf(420, 42, 423)))
    }

    @Test
    fun generateRandom() {
        val min = 100
        val max = 200
        val first: Int = sut.generateRandom(min, max)
        val second: Int= sut.generateRandom(min, max)
        val third: Int = sut.generateRandom(min, max)
        println(first)
        println(second)
        println(third)

        assertTrue(first in min..<max)
        assertTrue(second in min..<max)
        assertTrue(third in min..<max)
        assertTrue(listOf(first, second, third).distinct().size > 1)
    }

    @Test
    fun shuffleArray() {
        val arr = arrayOf(1,2,3,4,5,6,7,8,9,10).toIntArray()
        val first = sut.shuffleArray(arr)
        val second = sut.shuffleArray(arr)
        val third = sut.shuffleArray(arr)
        println(first.contentToString())
        println(second.contentToString())
        println(third.contentToString())

      assertNotEquals(arr.contentToString(), first.contentToString())
      assertNotEquals(arr.contentToString(), second.contentToString())
      assertNotEquals(arr.contentToString(), third.contentToString())
      assertTrue(listOf(first.contentToString(), second.contentToString(), third.contentToString()).distinct().size > 1)
    }

    @Test
    fun levenshtein() {
        assertEquals(3, sut.levenshtein("kitten", "sitting"))
        assertEquals(8, sut.levenshtein("rosettacode", "raisethysword"))
  }


    @Test
    fun fizzbuzz() {
        assertEquals("FizzBuzz", sut.fizzbuzz(195))
        assertEquals("Fizz", sut.fizzbuzz(81))
        assertEquals("Buzz", sut.fizzbuzz(70))
        assertEquals("", sut.fizzbuzz(56))
    }

    @Test
    fun fizzBuzzPlus() {
        assertEquals("FizzBuzz", sut.fizzBuzzPlus(195))
        assertEquals("Fizz", sut.fizzBuzzPlus(81))
        assertEquals("Buzz", sut.fizzBuzzPlus(70))
        assertEquals("Buzz", sut.fizzBuzzPlus(56))
        assertEquals("Fizz", sut.fizzBuzzPlus(13))
        assertEquals("FizzBuzz", sut.fizzBuzzPlus(53))
        assertEquals("", sut.fizzBuzzPlus(19))
    }
}




//100 doors             -- done
//12 Days of Xmas       -- done
//ABC Problem           -- done
//Align Columns
//Anagrams              -- done
//Array Shuffle         -- done
//Balanced Parentheses  -- done
//Best Shuffle
//Bowling Game
//Calc Stats            -- done
//Closest To Zero       -- done
//Combined Number       -- done
//Count Coins           -- done (sorta)
//Diff Selector wont
//Diversion
//Eight Queens ?
//Filename Range
//Fisher-Yates Shuffle
//Five Weekends         -- done
//Fizz Buzz             -- done
//Fizz Buzz Plus        -- done
//Friday 13th           -- done
//Game of Life
//Gray Code wont
//Group Neighbours wont
//Haiku Review
//Harry Potter
//ISBN                  -- done
//Knight's Tour ?
//LCD Digits            -- done
//Leap Years            -- done
//Levenshtein Distance  -- done
//Longest Common Prefix -- done
//Magic Square          -- done
//Mars Rover ?
//Mine Field            -- done
//Mine Sweeper ?
//Monty Hall
//Number Chains         -- done
//Number Names          -- done
//Phone Numbers         -- done
//Poker Hands           -- done
//Prime Factors         -- done
//Print Diamond         -- done
//Recently Used List    -- done
//Remove Duplicates     -- done
//Reordering            -- done
//Reverse Roman         -- done
//Reversi ?
//Roman Numerals        -- done
//Saddle Points         -- done
//Tennis                -- done
//Tiny Maze             -- done
//Unsplice              -- done
//Vending Machine ?
//Wonderland Number     -- done
//Word Wrap             -- done
//Yatzy
//Yatzy Cutdown
//Zeckendorf Number

