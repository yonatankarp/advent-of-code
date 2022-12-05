package com.yonatankarp.adventofcode.aoc2022.day01

import com.yonatankarp.adventofcode.utils.readPuzzleInput

fun main() {
    println("The 🧝 carrying the most calories have: ${caloriesCountTopElf()}")
    println("The 3 top 🧝 carrying the most calories have: ${caloriesCountTopThreeElves()}")
}

fun caloriesCountTopElf(input: List<String> = readPuzzleInput(1)) =
    caloriesCount(input).maxOf { it }

fun caloriesCountTopThreeElves(input: List<String> = readPuzzleInput(1)) =
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
