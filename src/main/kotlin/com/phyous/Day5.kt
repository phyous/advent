package com.phyous

import com.phyous.lib.Helpers
import java.lang.StringBuilder

object Day5 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Helpers.getProblemData("day5")
        val answer1 = solution1(input[0])
        println("solution1: $answer1")

        val answer2 = solution2(input[0])
        println("solution2: $answer2")
    }

    fun solution1(input: String): Int {
        return react(input).length
    }

    fun react(input: String): String {
        var ret = input.toCharArray().toList()
        var lastSize: Int? = null

        while (ret.size != lastSize) {
            lastSize = ret.size
            ret = singleReaction(ret)
        }

        return ret.fold(StringBuilder()) { sb, e -> sb.append(e); sb }.toString()
    }

    val DOWNCASE_ASCII = 32

    fun singleReaction(input: List<Char>): List<Char> {
        val ret = mutableListOf<Char>()

        var i = 0
        while (i < input.size) {
            val cur = input.get(i)
            if (i == input.size - 1) {
                ret.add(cur)
                i += 1
                continue
            }
            val next = input.get(i + 1)

            if (Math.abs(cur - next) == DOWNCASE_ASCII) {
                i += 2
            } else {
                ret.add(cur)
                i += 1
            }
        }
        return ret
    }

    fun removeReact(input: String): String {
        return CharRange('a', 'z').map { c ->
            val newStr = input.toCharArray().toList().fold(StringBuilder()) { sb, char ->
                if (char != c && char != c - DOWNCASE_ASCII) {
                    sb.append(char)
                }
                sb
            }.toString()
            react(newStr)
        }.minBy { s -> s.length }!!
    }

    fun solution2(input: String): Int {
        return removeReact(input).length
    }
}