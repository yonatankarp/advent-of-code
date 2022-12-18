package com.yonatankarp.adventofcode.aoc2022.day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day09Test {

    private val exampleInput1 = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent().split("\n")

    private val exampleInput2 = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day09 = Day09(exampleInput1)
            assertEquals(13, day09.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day09 = Day09(resourceAsList("2022/day09.txt"))
            assertEquals(5960, day09.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example1`() {
            val day09 = Day09(exampleInput1)
            assertEquals(1, day09.solvePart2())
        }

        @Test
        fun `Part 2 - Example2`() {
            val day09 = Day09(exampleInput2)
            assertEquals(36, day09.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day09 = Day09(resourceAsList("2022/day09.txt"))
            assertEquals(2327, day09.solvePart2())
        }
    }
}
