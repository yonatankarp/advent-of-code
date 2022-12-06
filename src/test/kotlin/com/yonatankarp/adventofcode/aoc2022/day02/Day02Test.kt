package com.yonatankarp.adventofcode.aoc2022.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day02Test {

    private val exampleInput = """
        A Y
        B X
        C Z
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day02 = Day02(exampleInput)
            assertEquals(15, day02.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day02 = Day02(resourceAsList("2022/day02.txt"))
            assertEquals(10994, day02.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day02 = Day02(exampleInput)
            assertEquals(12, day02.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day02 = Day02(resourceAsList("2022/day02.txt"))
            assertEquals(12526, day02.solvePart2())
        }
    }
}
