package com.yonatankarp.adventofcode.year2022

import com.yonatankarp.adventofcode.utils.readPuzzleInput

fun main() {
    println("The sum of priorities of all 🎁 is ${calculatePriorities()}")
}

fun calculatePriorities(input: List<String> = readPuzzleInput(3)): Int {
    val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()
    return input
        .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .map { it.first.filter { c -> it.second.contains(c) }.toSet() }
        .flatMap { it.map { c -> priorities[c]!! } }
        .sumOf { it }
}
