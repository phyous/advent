package com.phyous

import com.phyous.Day4.millisToMinute
import com.phyous.Day4.parseEntry
import com.phyous.Day4.sleepIntervals
import com.phyous.Day4.sleepMinutes
import com.phyous.Day4.solution1
import com.phyous.Day4.solution2
import org.joda.time.DateTime
import org.joda.time.Interval
import org.junit.Test

import org.junit.Assert.*

class Day4Test {

    @Test
    fun parseEntryTest() {
        val str = "[1518-11-01 23:58] Guard #99 begins shift"

        val ret = parseEntry(str)
        val date = DateTime(1518, 11, 1, 23, 58)
        val id = "99"
        val action = Day4.Action.START

        assertEquals(ret, Day4.Entry(date, id, action))
    }

    @Test
    fun sleepIntervalsTest() {
        val strs = listOf(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up"
        )
        val shiftMap = sleepIntervals(strs)
        val i1 = Interval(
                DateTime(1518, 11, 1, 0, 5),
                DateTime(1518, 11, 1, 0, 25)
        )
        val i2 = Interval(
                DateTime(1518, 11, 1, 0, 30),
                DateTime(1518, 11, 1, 0, 55)
        )
        val expected = mapOf("10" to listOf(i1, i2))

        assertEquals(expected, shiftMap)
    }

    @Test
    fun sleepMinutesTest() {
        val strs = listOf(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up"
        )
        val shiftMap = sleepIntervals(strs)

        val duration = sleepMinutes(shiftMap["10"]!!)

        assertEquals(45, duration)
    }

    @Test
    fun millisToMinuteTest() {
        val millis = DateTime(1518, 11, 1, 0, 30).millisOfDay().get()
        val min = millisToMinute(millis)
        assertEquals(30, min)
    }

    @Test
    fun solution1Test() {
        val input = listOf(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up",
                "[1518-11-01 23:58] Guard #99 begins shift",
                "[1518-11-02 00:40] falls asleep",
                "[1518-11-02 00:50] wakes up",
                "[1518-11-03 00:05] Guard #10 begins shift",
                "[1518-11-03 00:24] falls asleep",
                "[1518-11-03 00:29] wakes up",
                "[1518-11-04 00:02] Guard #99 begins shift",
                "[1518-11-04 00:36] falls asleep",
                "[1518-11-04 00:46] wakes up",
                "[1518-11-05 00:03] Guard #99 begins shift",
                "[1518-11-05 00:45] falls asleep",
                "[1518-11-05 00:55] wakes up"
        )

        val ret = solution1(input)
        assertEquals(240, ret)
    }

    @Test
    fun solution2Test() {
        val input = listOf(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up",
                "[1518-11-01 00:30] falls asleep",
                "[1518-11-01 00:55] wakes up",
                "[1518-11-01 23:58] Guard #99 begins shift",
                "[1518-11-02 00:40] falls asleep",
                "[1518-11-02 00:50] wakes up",
                "[1518-11-03 00:05] Guard #10 begins shift",
                "[1518-11-03 00:24] falls asleep",
                "[1518-11-03 00:29] wakes up",
                "[1518-11-04 00:02] Guard #99 begins shift",
                "[1518-11-04 00:36] falls asleep",
                "[1518-11-04 00:46] wakes up",
                "[1518-11-05 00:03] Guard #99 begins shift",
                "[1518-11-05 00:45] falls asleep",
                "[1518-11-05 00:55] wakes up"
        )

        val ret = solution2(input)
        assertEquals(4455, ret)
    }
}