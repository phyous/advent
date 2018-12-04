package com.phyous

import org.junit.Test

import org.junit.Assert.*

class Day1Test {

    @Test
    fun solution2a() {
        val input = listOf("+1", "-1")
        assertEquals(0, Day1.solution2(input))
    }

    @Test
    fun solution2b() {
        val input = listOf("+3", "+3", "+4", "-2", "-4")
        assertEquals(10, Day1.solution2(input))
    }

    @Test
    fun solution2c() {
        val input = listOf("-6", "+3", "+8", "+5", "-6")
        assertEquals(5, Day1.solution2(input))
    }

    @Test
    fun solution2d() {
        val input = listOf("+7", "+7", "-2", "-7", "-4")
        assertEquals(14, Day1.solution2(input))
    }
}