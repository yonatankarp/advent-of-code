package com.yonatankarp.adventofcode.aoc2022.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day08Test {

    private val exampleInput = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day08 = Day08(exampleInput)
            assertEquals(21, day08.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day08 = Day08(resourceAsList("2022/day08.txt"))
            assertEquals(1698, day08.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day08 = Day08(exampleInput)
            assertEquals(8, day08.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day08 = Day08(resourceAsList("2022/day08.txt"))
            assertEquals(672280, day08.solvePart2())
        }
    }
}
