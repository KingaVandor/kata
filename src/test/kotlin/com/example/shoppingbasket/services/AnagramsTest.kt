package com.example.shoppingbasket.services

import kotlin.test.Test
import kotlin.test.assertTrue

class AnagramsTest {
    private val sut = Anagrams()

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
}