package com.example.shoppingbasket.services

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test


class FiveWeekEndsTest {
    private val sut = FiveWeekends()

    @Test
    fun fiveWeekends() {
        assertEquals(Pair(201, 29), sut.fiveWeekends())
    }

}

