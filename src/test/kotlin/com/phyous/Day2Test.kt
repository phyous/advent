package com.phyous

import com.phyous.Day2.countLetterGroups
import com.phyous.Day2.diffChars
import com.phyous.Day2.nCount
import com.phyous.Day2.solution1
import org.junit.Test

import org.junit.Assert.*

class Day2Test {

    @Test
    fun nCountTest() {
        val input1 = mapOf('a' to 1, 'b' to 2, 'c' to 3)
        assertEquals(true, nCount(2)(input1))
        assertEquals(false, nCount(4)(input1))
    }

    @Test
    fun countLetterGroupsTest() {
        val input1 = "abbcccdd".toCharArray().toList()
        assertEquals(countLetterGroups(input1), Pair(true, true))

        val input2 = "caabbbacc".toCharArray().toList()
        assertEquals(countLetterGroups(input2), Pair(false, true))

        val input3 = "".toCharArray().toList()
        assertEquals(countLetterGroups(input3), Pair(false, false))
    }

    @Test
    fun solution1Test() {
        val input1 = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        assertEquals(12, solution1(input1))
    }

    @Test
    fun diffCharsTest() {
        val s1 = "qywzphxoiseldjrntfygvdmanu"
        val s2 = "qyszphxoiseldjrntfygvdmanu"
        assertEquals("qyzphxoiseldjrntfygvdmanu", diffChars(s1, s2))
    }
}