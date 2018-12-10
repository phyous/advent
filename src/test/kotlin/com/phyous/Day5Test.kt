package com.phyous

import com.phyous.Day5.react
import com.phyous.Day5.removeReact
import com.phyous.Day5.singleReaction
import com.phyous.Day5.solution1
import org.junit.Test

import org.junit.Assert.*

class Day5Test {

    @Test
    fun singleReactionTest() {
        assertEquals("".toCharArray().toList(),
                singleReaction("aA".toCharArray().toList()))

        assertEquals("".toCharArray().toList(),
                singleReaction("Aa".toCharArray().toList()))

        assertEquals("b".toCharArray().toList(),
                singleReaction("aAb".toCharArray().toList()))

        assertEquals("dabAaCBAcaDA".toCharArray().toList(),
                singleReaction("dabAcCaCBAcCcaDA".toCharArray().toList()))
    }

    @Test
    fun reactTest() {
        assertEquals("dabCBAcaDA", react("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun removeReactTest() {
        assertEquals("daDA", removeReact("dabAcCaCBAcCcaDA"))
    }
}