package com.yonatankarp.adventofcode.aoc2022.day03

class Day03(private val input: List<String>) {

    private val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()

    fun solvePart1(): Int =
        input
            .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
            .flatMap { it.first.filter { c -> it.second.contains(c) }.toSet() }
            .sumOf { priorities[it]!! }

    fun solvePart2(): Int =
        input
            .chunked(3)
            .flatMap { it.first().filter { c -> c in it[1] && c in it[2] }.toSet() }
            .sumOf { priorities[it]!! }
}
