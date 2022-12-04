package com.yonatankarp.adventofcode.year2022.day03

import com.yonatankarp.adventofcode.utils.readPuzzleInput

fun main() {
    println("The sum of priorities of all 🎁 is ${calculatePriorities()}")
    println("The sum of priorities of all 🎁 for 🧝🧝🧝 is ${calculateElvesPriorities()}")
}

fun calculatePriorities(input: List<String> = readPuzzleInput(3)): Int {
    val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()
    return input
        .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .flatMap { it.first.filter { c -> it.second.contains(c) }.toSet() }
        .sumOf { priorities[it]!! }
}

fun calculateElvesPriorities(input: List<String> = readPuzzleInput(3)): Int {
    val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()
    return input
        .chunked(3)
        .flatMap { it.first().filter { c -> c in it[1] && c in it[2] }.toSet() }
        .sumOf { priorities[it]!! }
}
