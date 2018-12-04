package com.phyous

import com.phyous.lib.Helpers

object Day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Helpers.getProblemData("day1")
        val answer1 = solution1(input)
        println("solution1: $answer1")

        val answer2 = solution2(input)
        println("solution2: $answer2")
    }

    fun solution1(input: List<String>): Int {
        val ints = input.map { x -> Integer.parseInt(x) }
        return ints.fold(0) { sum, e -> sum + e }
    }

    // What is the first frequency your device reaches twice?
    fun solution2(input: List<String>): Int {
        val ints = input.map { x -> Integer.parseInt(x) }
        val seen: MutableSet<Int> = mutableSetOf(0)

        var seenTwice: Int? = null
        var seed = 0

        while (seenTwice == null) {
            seed = ints.fold(seed) { sum, e ->
                val ret = sum + e
                if(seen.contains(ret) && seenTwice == null) {
                    seenTwice = ret
                } else {
                    seen.add(ret)
                }
                ret
            }
        }
        return seenTwice as Int
    }
}