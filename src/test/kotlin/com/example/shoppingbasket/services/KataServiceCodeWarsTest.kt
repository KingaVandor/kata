package com.example.shoppingbasket.services

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test



class KataServiceCodeWarsTest {

    private val margin = 0.001
    private val sut = KataServiceCodeWars()

    @Test
    fun regularCases() {
        assertEquals(3.571, sut.catchTheBus("4:53 PM" to "5:00 PM", "4:47 PM" to "4:55 PM"), margin)
        assertEquals(18.75, sut.catchTheBus("7:58 AM" to "8:02 AM", "7:55 AM" to "8:01 AM"), margin)
//        assertEquals(42.013, sut.catchTheBus("12:00 PM" to "12:12 PM", "11:59 AM" to "12:11 PM"), margin)
//        assertEquals(48.148, sut.catchTheBus("11:35 AM" to "12:02 PM", "11:45 AM" to "11:51 AM"), margin)
//        assertEquals(62.5, sut.catchTheBus("6:22 PM" to "6:23 PM", "6:21 PM" to "6:25 PM"), margin)
//        assertEquals(91.667, sut.catchTheBus("12:58 PM" to "1:02 PM", "1:00 PM" to "1:06 PM"), margin)
    }

    @Test
    fun edgeCases() {
        assertEquals(0.0, sut.catchTheBus("4:53 PM" to "5:00 PM", "4:47 PM" to "4:52 PM"), margin)
        assertEquals(100.0, sut.catchTheBus("9:22 AM" to "9:23 AM", "9:24 AM" to "9:44 AM"), margin)
    }

    @Test
    fun incrementStringTests() {
        doTest("", "1")
        doTest("010", "011")
        doTest("999", "1000")
        doTest("foobar000", "foobar001")
        doTest("foobar999", "foobar1000")
        doTest("foobar00999", "foobar01000")
        doTest("foo", "foo1")
        doTest("foobar001", "foobar002")
        doTest("fo99obar99", "fo99obar100")
        doTest("foobar1", "foobar2")
        doTest("1", "2")
        doTest("", "1")
        doTest("009", "010")
    }

    private fun doTest(input: String, expected: String) {
        val message = String.format("for input: \"%s\"\n", input)
        val actual_ = sut.incrementString(input)
        assertEquals(expected, actual_, message)
    }

    @Test
    fun testFixed() {
        assertEquals("000000", sut.rgb(0, 0, 0))
        assertEquals("000000", sut.rgb(0, 0, -20))
        assertEquals("FFFFFF", sut.rgb(300,255,255))
        assertEquals("ADFF2F", sut.rgb(173,255,47))
        assertEquals("9400D3", sut.rgb(148, 0, 211))
    }
}




