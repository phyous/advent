package com.phyous

import com.phyous.lib.Helpers
import org.joda.time.DateTime
import org.joda.time.Interval
import java.util.regex.Pattern

object Day4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Helpers.getProblemData("day4")
        val answer1 = solution1(input)
        println("solution1: $answer1")

        val answer2 = solution2(input)
        println("solution2: $answer2")
    }

    enum class Action {START, SLEEP, WAKEUP}

    data class Entry(val time: DateTime, val guardId: String?, val action: Action)

    val DATE_PATTERN = Pattern.compile("\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})\\] (.*)")
    val GUARD_PATTERN = Pattern.compile("Guard #(\\d+) begins shift")

    fun determineAction(str: String): Action {
        if(str.startsWith("falls")) {
            return Action.SLEEP
        } else if (str.startsWith("wakes")) {
            return Action.WAKEUP
        } else {
            return Action.START
        }
    }

    fun parseEntry(input: String): Entry {
        val matcher = DATE_PATTERN.matcher(input)
        matcher.find()
        val year = matcher.group(1)!!.toInt()
        val month = matcher.group(2)!!.toInt()
        val day = matcher.group(3)!!.toInt()
        val hour = matcher.group(4)!!.toInt()
        val minute = matcher.group(5)!!.toInt()
        val remainder = matcher.group(6)!!

        val date = DateTime(year, month, day, hour, minute)
        var guard: String? = null
        if (remainder.startsWith("Guard")) {
            val guardMatcher = GUARD_PATTERN.matcher(remainder)
            guardMatcher.find()
            guard = guardMatcher.group(1)
        }
        val action = determineAction(remainder)

        return Entry(date, guard, action)
    }

    fun sleepIntervals(entries: List<String>): Map<String, List<Interval>> {
        var id: String? = null
        var lastEntry: Entry? = null

        return entries.fold(mutableMapOf<String, MutableList<Interval>>()) { m, s ->
            val entry = parseEntry(s)
            if (entry.action == Action.START) {
                id = entry.guardId!!
            } else if (entry.action == Action.SLEEP) {
                lastEntry = entry
            } else if (entry.action == Action.WAKEUP) {
                if (m[id] == null) {
                    m.put(id!!, mutableListOf(Interval(lastEntry!!.time, entry.time)))
                } else {
                    m[id]!!.add(Interval(lastEntry!!.time, entry.time))
                }
            }
            m
        }
    }

    val MINUTE_IN_MILLIS = 60 * 1000

    fun sleepMinutes(intervals: List<Interval>):Long {
        return intervals.map { i -> i.toDurationMillis() }.sum() / MINUTE_IN_MILLIS
    }

    fun mostSlept(intervals: List<Interval>): Pair<Int, Int> {
        val minuteMap = mutableMapOf<Int, Int>()
        intervals.forEach { i ->
            var curTime = i.start!!
            while(i.contains(curTime)) {
                val min = millisToMinute(curTime.millisOfDay().get())
                minuteMap.put(min, minuteMap.getOrDefault(min, 0) + 1)
                curTime = curTime.plusMinutes(1)
            }
        }
        val mostSlept = minuteMap.entries.maxBy { e -> e.value }!!
        val minute = mostSlept.key
        val count = mostSlept.value
        return Pair(minute, count)
    }

    fun millisToMinute(millis: Int): Int {
        val minute = (millis % (60 * MINUTE_IN_MILLIS)) / MINUTE_IN_MILLIS
        return minute
    }

    fun solution1(strs: List<String>): Long {
        val intervalMap = sleepIntervals(strs.sorted())

        // Pair<ID, sleepiestMinute>
        val sleepiest = intervalMap
                .map { e -> Pair(e.key, sleepMinutes(e.value)) }
                .maxBy { pair -> pair.second}!!

        return mostSlept(intervalMap[sleepiest.first]!!).first * sleepiest.first.toLong()
    }

    fun solution2(strs: List<String>): Long {
        val intervalMap = sleepIntervals(strs.sorted())

        val mostSleep = intervalMap.entries
                .map { e -> Pair(e.key, mostSlept(e.value)) }
                .maxBy { e -> e.second.second }!!

        return mostSleep.first.toLong() * mostSleep.second.first
    }
 }