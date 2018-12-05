package com.phyous

import com.phyous.lib.Helpers
import java.util.regex.Pattern

object Day3 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Helpers.getProblemData("day3")
        val answer1 = solution1(input)
        println("solution1: $answer1")

        val answer2 = solution2(input)
        println("solution2: $answer2")
    }

    data class Claim(val id: Int, val leftDist: Int, val topDist: Int, val width: Int, val height: Int)

    // Solution 1 -------------
    fun solution1(claimStrings: List<String>): Int {
        val coordinates = generateAllCoordinates(claimStrings.map { s -> parseClaimString(s) })
        return coordinates.entries.filter { v -> v.value >= 2}.size
    }

    fun parseClaimString(input:String): Claim {
        val pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)")
        val matcher = pattern.matcher(input)
        matcher.find()
        val id = matcher.group(1)!!.toInt()
        val leftDist = matcher.group(2)!!.toInt()
        val topDist = matcher.group(3)!!.toInt()
        val width = matcher.group(4)!!.toInt()
        val height = matcher.group(5)!!.toInt()

        return Claim(id, leftDist, topDist, width, height)
    }

    fun getFabricCoordinates(claim: Claim): Map<Pair<Int,Int>, Int> {
        val coordinates = mutableMapOf<Pair<Int, Int>, Int>()

        for(x in claim.leftDist until claim.leftDist + claim.width) {
            for(y in claim.topDist until claim.topDist + claim.height) {
                coordinates.put(Pair(x, y), 1)
            }
        }
        return coordinates
    }

    fun generateAllCoordinates(claims: List<Claim>): Map<Pair<Int,Int>, Int> {
        return claims.fold(mutableMapOf()) { map, claim ->
            getFabricCoordinates(claim).forEach {k, v ->
                map.merge(k, v) { oldVal, newVal -> oldVal + newVal }
            }
            map
        }
    }

    // Solution 2 ---------
    fun solution2(claimStrings: List<String>): Int {
        val claims = claimStrings.map { s -> parseClaimString(s) }
        val coordinates = generateAllCoordinates(claims)

        claims.forEach{c ->
            if(checkMap(c, coordinates)) return c.id
        }
        return -1
    }

    fun checkMap(claim:Claim, coordinates:Map<Pair<Int, Int>, Int>): Boolean {
        for(x in claim.leftDist until claim.leftDist + claim.width) {
            for(y in claim.topDist until claim.topDist + claim.height) {
                if(coordinates[Pair(x, y)]!! > 1) return false
            }
        }
        return true
    }
}