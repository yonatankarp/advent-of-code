package com.yonatankarp.adventofcode.aoc2022.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day03Test {

    private val exampleInput = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day03 = Day03(exampleInput)
            assertEquals(157, day03.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day03 = Day03(resourceAsList("2022/day03.txt"))
            assertEquals(8153, day03.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day03 = Day03(exampleInput)
            assertEquals(70, day03.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day03 = Day03(resourceAsList("2022/day03.txt"))
            assertEquals(2342, day03.solvePart2())
        }
    }
}
