package com.yonatankarp.adventofcode.aoc2022.day06

import com.yonatankarp.adventofcode.utils.hasDuplicates

class Day06(private val input: String) {

    private fun solve(windowSize: Int) =
        input
            .windowed(size = windowSize, step = 1, partialWindows = false)
            .withIndex()
            .first { !it.value.hasDuplicates() }
            .index + windowSize

    fun solvePart1(): Int = solve(PACKAGE_MARKER_WINDOW_SIZE)

    fun solvePart2(): Int = solve(MESSAGE_MARKER_WINDOW_SIZE)

    companion object {
        private const val PACKAGE_MARKER_WINDOW_SIZE = 4
        private const val MESSAGE_MARKER_WINDOW_SIZE = 14
    }
}
