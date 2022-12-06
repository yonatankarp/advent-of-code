package com.yonatankarp.adventofcode.aoc2022.day01

class Day01(private val input: List<String>) {

    fun solvePart1(): Int =
        caloriesCount(input)
            .maxOf { it }

    fun solvePart2(): Int =
        caloriesCount(input)
            .sorted()
            .takeLast(3)
            .sum()

    private fun caloriesCount(input: List<String>) =
        input.fold(mutableListOf(0)) { elves, snack ->
            if (snack.isEmpty()) elves.add(0)
            else elves[elves.lastIndex] = elves.last() + snack.toInt()
            elves
        }
}
