package com.yonatankarp.adventofcode.aoc2022.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day01Test {

    private val exampleInput = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day01 = Day01(exampleInput)
            assertEquals(24000, day01.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day01 = Day01(resourceAsList("2022/day01.txt"))
            assertEquals(69177, day01.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day01 = Day01(exampleInput)
            assertEquals(45000, day01.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day01 = Day01(resourceAsList("2022/day01.txt"))
            assertEquals(207456, day01.solvePart2())
        }
    }
}
