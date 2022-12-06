package com.yonatankarp.adventofcode.aoc2022.day04

import com.yonatankarp.adventofcode.utils.anyContainedIn
import com.yonatankarp.adventofcode.utils.containedIn

class Day04(private val input: List<String>) {

    fun solvePart1(): Int =
        input.filter { it.isNotBlank() }
            .map {
                val (firstElf, secondElf) = it.split(",")
                toIntRange(firstElf) to toIntRange(secondElf)
            }
            .count { it.first.containedIn(it.second) || it.second.containedIn(it.first) }

    fun solvePart2(): Int =
        input.filter { it.isNotBlank() }
            .map {
                val (firstElf, secondElf) = it.split(",")
                toIntRange(firstElf) to toIntRange(secondElf)
            }
            .count { it.first.anyContainedIn(it.second) || it.second.anyContainedIn(it.first) }

    private fun toIntRange(range: String): IntRange {
        val (start, end) = range.split("-")
        return start.toInt()..end.toInt()
    }
}
