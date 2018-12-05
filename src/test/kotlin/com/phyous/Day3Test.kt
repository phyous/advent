package com.phyous

import com.phyous.Day3.generateAllCoordinates
import com.phyous.Day3.getFabricCoordinates
import com.phyous.Day3.parseClaimString
import com.phyous.Day3.solution1
import org.junit.Test

import org.junit.Assert.*

class Day3Test {

    @Test
    fun getFabricCoordinatesTest() {
        val coordinateMap = getFabricCoordinates(Day3.Claim(1, 5, 5, 2, 2))
        val expected = mapOf(Pair(5,5) to 1, Pair(5,6) to 1, Pair(6,5) to 1, Pair(6,6) to 1)
        assertEquals(expected, coordinateMap)
    }

    @Test
    fun generateAllCoordinatesTest() {
        val claim1 = Day3.Claim(1, 1, 1, 2, 2)
        val claim2 = Day3.Claim(2, 2, 2, 2, 2)
        val allCoordinates = generateAllCoordinates(listOf(claim1, claim2))
        val expected = mapOf(
                Pair(1,1) to 1, Pair(1,2) to 1,
                Pair(2,1) to 1, Pair(2,2) to 2, Pair(2,3) to 1,
                                Pair(3,2) to 1, Pair(3,3) to 1)
        assertEquals(expected, allCoordinates)
    }

    @Test
    fun parseClaimStringTest() {
        val str = "#1287 @ 272,638: 10x28"
        val parsed = parseClaimString(str)
        val expected = Day3.Claim(1287, 272, 638, 10, 28)

        assertEquals(expected, parsed)
    }

    @Test
    fun solution1Test() {
        val strs = listOf("#1 @ 1,1: 2x2", "#2 @ 2,2: 2x2")
        assertEquals(1, solution1(strs))
    }
}