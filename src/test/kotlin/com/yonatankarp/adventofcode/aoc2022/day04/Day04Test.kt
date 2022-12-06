package com.yonatankarp.adventofcode.aoc2022.day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day04Test {

    private val exampleInput = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day04 = Day04(exampleInput)
            assertEquals(2, day04.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day04 = Day04(resourceAsList("2022/day04.txt"))
            assertEquals(556, day04.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day04 = Day04(exampleInput)
            assertEquals(4, day04.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day04 = Day04(resourceAsList("2022/day04.txt"))
            assertEquals(876, day04.solvePart2())
        }
    }
}
