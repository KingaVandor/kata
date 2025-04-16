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
        assertEquals("FFFFFF", sut.rgb(300, 255, 255))
        assertEquals("ADFF2F", sut.rgb(173, 255, 47))
        assertEquals("9400D3", sut.rgb(148, 0, 211))
    }

    @Test
    fun testFormatDurationExamples() {
        assertEquals("1 second", sut.formatDuration(1))
        assertEquals("1 minute and 2 seconds", sut.formatDuration(62))
        assertEquals("2 minutes", sut.formatDuration(120))
        assertEquals("1 hour", sut.formatDuration(3600))
        assertEquals("1 hour, 1 minute and 2 seconds", sut.formatDuration(3662))
    }

    @Test
    fun testStripComments() {
        assertEquals("apples, plums\npears\noranges", sut.stripComments("apples, plums % and bananas\npears\noranges !applesauce", charArrayOf('%', '!')))
        assertEquals("Q\nu\ne", sut.stripComments("Q @b\nu\ne -e f g", charArrayOf('@', '-')))
    }

    @Test
    fun testSumOfDivided() {
        assertEquals("", sut.sumOfDivided(intArrayOf()))
        assertEquals("(2 12)(3 27)(5 15)", sut.sumOfDivided(intArrayOf(12, 15)))
        assertEquals("(2 54)(3 135)(5 90)(7 21)", sut.sumOfDivided(intArrayOf(15, 21, 24, 30, 45)))
        assertEquals("(2 1032)(3 453)(5 310)(7 126)(11 110)(17 204)(29 116)(41 123)(59 118)(79 158)(107 107)",
            sut.sumOfDivided(intArrayOf(107, 158, 204, 100, 118, 123, 126, 110, 116, 100)))
        assertEquals("(2 12620)(3 4530)(5 12620)(7 1260)(11 1100)(17 2040)(29 1160)(41 1230)(59 1180)(79 1580)(107 1070)",
            sut.sumOfDivided(intArrayOf(1070, 1580, 2040, 1000, 1180, 1230, 1260, 1100, 1160, 1000)))
    }

}




