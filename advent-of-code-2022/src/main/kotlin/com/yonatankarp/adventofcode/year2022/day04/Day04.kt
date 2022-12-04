package com.yonatankarp.adventofcode.year2022.day04

import com.yonatankarp.adventofcode.utils.readPuzzleInput

fun main() {
    println("Number of 🧝 that are fully contained within their partner range: ${numberOfFullyContainedElves()}")
    println("Number of 🧝 that are overlapping is: ${numberOfOverlappingElves()}")
}

fun numberOfFullyContainedElves(input: List<String> = readPuzzleInput(4)) =
    input
        .filter { it.isNotBlank() }
        .map {
            val (firstElf, secondElf) = it.split(",")
            toIntRange(firstElf) to toIntRange(secondElf)
        }
        .count { it.first.containedIn(it.second) || it.second.containedIn(it.first) }

private fun toIntRange(range: String): IntRange {
    val (start, end) = range.split("-")
    return start.toInt()..end.toInt()
}

fun IntRange.containedIn(other: IntRange) =
    this.first >= other.first && this.last <= other.last

fun numberOfOverlappingElves(input: List<String> = readPuzzleInput(4)) =
    input
        .filter { it.isNotBlank() }
        .map {
            val (firstElf, secondElf) = it.split(",")
            toIntRange(firstElf) to toIntRange(secondElf)
        }
        .count { it.first.anyContainedIn(it.second) || it.second.anyContainedIn(it.first) }

fun IntRange.anyContainedIn(other: IntRange) =
    this.any { other.contains(it) }
