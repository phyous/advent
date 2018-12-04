package com.phyous

import com.phyous.lib.Helpers
import java.lang.StringBuilder

object Day2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Helpers.getProblemData("day2")
        val answer1 = solution1(input)
        println("solution1: $answer1")

        val answer2 = solution2(input)
        println("solution2: $answer2")
    }

    fun differByOne(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false

        return (0 until s1.length).fold(0) { diff, pos ->
            diff + (if (s1.get(pos) != s2.get(pos)) 1 else 0)
        } == 1
    }

    fun diffChars(s1: String, s2: String): String {
        return s1.toCharArray().asList().foldIndexed(StringBuilder()){ i, sb, v ->
            if(s1.get(i) == s2.get(i)) sb.append(v)
            sb
        }.toString()
    }

    /**
    Confident that your list of box IDs is complete, you're ready to find the boxes full of prototype fabric.

    The boxes will have IDs which differ by exactly one character
    at the same position in both strings. For example, given the following box IDs:

    abcde
    fghij
    klmno
    pqrst
    fguij
    axcye
    wvxyz
    The IDs abcde and axcye are close, but they differ by two characters
    (the second and fourth). However, the IDs fghij and fguij differ by exactly one character,
    the third (h and u). Those must be the correct boxes.

    What letters are common between the two correct box IDs? (In the example above,
    this is found by removing the differing character from either ID, producing fgij.)
     */
    fun solution2(input: List<String>): String {
        for(i in 0 until input.size) {
            for(j in 0 until input.size) {
                if(differByOne(input[i], input[j])) {
                    println(input[i])
                    println(input[j])
                    return diffChars(input[i], input[j])
                }
            }
        }
        return ""
    }

    //------------
    fun nCount(n: Int) = fun(inputMap: Map<Char, Int>): Boolean {
        return inputMap.entries.fold(mutableSetOf<Char>()) { set, v ->
            if (v.value == n) set.add(v.key)
            set
        }.size > 0
    }

    fun countLetterGroups(id: List<Char>): Pair<Boolean, Boolean> {
        val frequencies =
                id.fold(mutableMapOf<Char, Int>()) { map, v ->
                    map[v] = (map[v] ?: 0) + 1
                    map
                }

        val twoCount = nCount(2)(frequencies)
        val threeCount = nCount(3)(frequencies)
        return Pair(twoCount, threeCount)
    }

    /**
    To make sure you didn't miss any, you scan the likely candidate boxes again, counting the
    number that have an ID containing exactly two of any letter and then separately counting
    those with exactly three of any letter. You can multiply those two counts together to get a
    rudimentary checksum and compare it to what your device predicts.

    For example, if you see the following box IDs:

    abcdef contains no letters that appear exactly two or three times.
    bababc contains two a and three b, so it counts for both.
    abbcde contains two b, but no letter appears exactly three times.
    abcccd contains three c, but no letter appears exactly two times.
    aabcdd contains two a and two d, but it only counts once.
    abcdee contains two e.
    ababab contains three a and three b, but it only counts once.

    Of these box IDs, four of them contain a letter which appears exactly twice, and three of them
    contain a letter which appears exactly three times. Multiplying these together produces a
    checksum of 4 * 3 = 12.
     */
    fun solution1(input: List<String>): Int {
        val counts = input.map { line ->
            countLetterGroups(line.toCharArray().asList())
        }.fold(Pair(0,0)) { pair, v ->
            val first = pair.first + (if (v.first) 1 else 0)
            val second = pair.second + (if (v.second) 1 else 0)
            Pair(first, second)
        }
        return counts.first * counts.second
    }
}