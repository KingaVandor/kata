package com.example.shoppingbasket.services

import com.example.shoppingbasket.services.TennisPlayer.*
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.Test
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
    fun encode() {
        assertEquals("", sut.encode(0))
        assertEquals("I", sut.encode(1))
        assertEquals("XXI", sut.encode(21))
        assertEquals("MMVIII", sut.encode(2008))
        assertEquals("MDCLXVI", sut.encode(1666))
    }

    @Test
    fun decode() {
        assertEquals(0, sut.decode(""))
        assertEquals(1, sut.decode("I"))
        assertEquals(21, sut.decode("XXI"))
        assertEquals(2008, sut.decode("MMVIII"))
        assertEquals(1666, sut.decode("MDCLXVI"))
        assertEquals(96, sut.decode("XCVI"))
        assertEquals(4, sut.decode("IV"))
        assertEquals(916, sut.decode("CMXVI"))
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
    fun spellOut() {
        assertEquals("zero", sut.spellOut(0))
        assertEquals("ninety nine", sut.spellOut(99))
        assertEquals("three hundred", sut.spellOut(300))
        assertEquals("three hundred and ten", sut.spellOut(310))
        assertEquals("one thousand, five hundred and one", sut.spellOut(1501))
        assertEquals("five hundred and twelve thousand, six hundred and seven", sut.spellOut(512607))
        assertEquals(
            "forty three million, one hundred and twelve thousand, six hundred and three",
            sut.spellOut(43112603)
        )

    }

    @Test
    fun inArray() {
        val a2 = arrayOf("lively", "alive", "harp", "sharp", "armstrong")
        assertArrayEquals(arrayOf("live", "strong"), sut.inArray(arrayOf<String>("xyz", "live", "strong"), a2))
        assertArrayEquals(arrayOf("arp", "live", "strong"), sut.inArray(arrayOf("live", "strong", "arp"), a2))
        assertArrayEquals(arrayOf<String>(), sut.inArray(arrayOf("tarp", "mice", "bull"), a2))
    }

//    @Test
//    fun pokerHands() {
//        assertEquals(
//            "Tie",
//            sut.pokerHands(
//                blackHand = listOf("2H", "3D", "5S", "9C", "KD"),
//                whiteHand = listOf("2D", "3H", "5C", "9S", "KH")
//            )
//        )
//        assertEquals(
//            "Black wins - High Card: 9",
//            sut.pokerHands(
//                blackHand = listOf("2H", "2D", "2S", "9C", "KD"),
//                whiteHand = listOf("2C", "3H", "4S", "5C", "KH")
//            )
//        )
//        assertEquals(
//            "White wins - High Card: Ace",
//            sut.pokerHands(
//                blackHand = listOf("2H", "3D", "5S", "9C", "KD"),
//                whiteHand = listOf("2C", "3H", "4S", "8C", "AH")
//            )
//        )
//    }

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
    fun anagramsBiro() {
        val expected: Set<String> = setOf(
            "biro", "bior", "brio", "broi", "boir", "bori",
            "ibro", "ibor", "irbo", "irob", "iobr", "iorb",
            "rbio", "rboi", "ribo", "riob", "roib", "robi",
            "obir", "obri", "oibr", "oirb", "orbi", "orib",
        )
        val actual = sut.anagrams("biro")
        assertTrue(expected.containsAll(actual))
        assertTrue(actual.containsAll(expected))

    }

    @Test
    fun anagramsA() {
        val expected: Set<String> = setOf(
            "a",
        )
        val actual = sut.anagrams("a")
        assertTrue(expected.containsAll(actual))
        assertTrue(actual.containsAll(expected))

    }

    @Test
    fun anagramsAB() {
        val expected: Set<String> = setOf(
            "ab", "ba"
        )
        val actual = sut.anagrams("ab")
        assertTrue(expected.containsAll(actual))
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun anagramsABC() {
        val expected: Set<String> = setOf(
            "abc", "acb", "bac", "bca", "cab", "cba",
        )
        val actual = sut.anagrams("abc")
        assertTrue(expected.containsAll(actual))
        assertTrue(actual.containsAll(expected))
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
        assertEquals(Door.CLOSED, sut.hundredDoors(2))
        assertEquals(Door.CLOSED, sut.hundredDoors(3))
        assertEquals(Door.OPEN, sut.hundredDoors(4))
        assertEquals(Door.OPEN, sut.hundredDoors(9))
        assertEquals(Door.CLOSED, sut.hundredDoors(12))
        assertEquals(Door.CLOSED, sut.hundredDoors(15))
        assertEquals(Door.OPEN, sut.hundredDoors(16))
        assertEquals(Door.OPEN, sut.hundredDoors(25))
        assertEquals(Door.OPEN, sut.hundredDoors(36))
        assertEquals(Door.CLOSED, sut.hundredDoors(42))
        assertEquals(Door.OPEN, sut.hundredDoors(49))
        assertEquals(Door.OPEN, sut.hundredDoors(64))
        assertEquals(Door.OPEN, sut.hundredDoors(81))
        assertEquals(Door.CLOSED, sut.hundredDoors(99))
        assertEquals(Door.OPEN, sut.hundredDoors(100))
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
        assertEquals(Pair(TennisScore.LOVE, null), sut.tennis(emptyList()))

        assertEquals(Pair(TennisScore.FIFTEEN, null), sut.tennis(listOf(A, B)))
        assertEquals(Pair(TennisScore.THIRTY, null), sut.tennis(listOf(A, B, A, B)))
        assertEquals(Pair(TennisScore.FORTY, null), sut.tennis(listOf(A, B, A, B, A, B)))
        assertEquals(Pair(TennisScore.DEUCE, null), sut.tennis(listOf(A, B, A, B, A, B, A, B)))

        assertEquals(Pair(TennisScore.FIFTEEN, A), sut.tennis(listOf(A)))
        assertEquals(Pair(TennisScore.THIRTY, A), sut.tennis(listOf(A, A, B)))
        assertEquals(Pair(TennisScore.FORTY, B), sut.tennis(listOf(A, B, A, B, B)))
        assertEquals(Pair(TennisScore.ADVANTAGE, A), sut.tennis(listOf(A, B, A, B, A, B, A)))
        assertEquals(Pair(TennisScore.GAME, B), sut.tennis(listOf(A, B, A, B, A, B, A, B, B)))
    }
}


//100 doors             -- done
//12 Days of Xmas       -- done
//ABC Problem           -- done
//Align Columns
//Anagrams              -- done
//Array Shuffle
//Balanced Parentheses  -- done
//Best Shuffle
//Bowling Game
//Calc Stats            -- done
//Closest To Zero       -- done
//Combined Number
//Count Coins
//Diff Selector
//Diversion
//Eight Queens
//Filename Range
//Fisher-Yates Shuffle
//Five Weekends
//Fizz Buzz             -- done
//Fizz Buzz Plus
//Friday 13th           -- done
//Game of Life
//Gray Code
//Group Neighbours
//Haiku Review
//Harry Potter
//ISBN                  -- done
//Knight's Tour
//LCD Digits            -- done
//Leap Years            -- done
//Levenshtein Distance
//Longest Common Prefix
//Magic Square
//Mars Rover
//Mine Field            -- done
//Mine Sweeper
//Monty Hall
//Number Chains
//Number Names          -- done
//Phone Numbers
//Poker Hands
//Prime Factors         -- done
//Print Diamond         -- done
//Recently Used List
//Remove Duplicates
//Reordering
//Reverse Roman         -- done
//Reversi
//Roman Numerals        -- done
//Saddle Points         -- done
//Tennis                -- done
//Tiny Maze             -- done
//Unsplice              -- done
//Vending Machine
//Wonderland Number
//Word Wrap             -- done
//Yatzy
//Yatzy Cutdown
//Zeckendorf Number

