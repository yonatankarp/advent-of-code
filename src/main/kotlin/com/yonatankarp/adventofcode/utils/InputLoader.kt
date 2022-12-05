package com.yonatankarp.adventofcode.utils

fun readPuzzleInput(number: Int): List<String> =
    readPuzzleInput("/2022/day${number.toString().padStart(2, '0')}.txt")

private fun readPuzzleInput(filename: String): List<String> =
    object {}.javaClass
        .getResource(filename)!!
        .readText()
        .split(System.lineSeparator())
