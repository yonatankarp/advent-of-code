package com.yonatankarp.adventofcode.aoc2022.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceSplitOnBlankLines
import org.junit.jupiter.api.Nested

class Day11Test {

    private val exampleInput = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent().split("\n\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day11 = Day11(exampleInput)
            assertEquals(10605L, day11.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day11 = Day11(resourceSplitOnBlankLines("2022/day11.txt"))
            assertEquals(110220L, day11.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day11 = Day11(exampleInput)
            assertEquals(2713310158L, day11.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day11 = Day11(resourceSplitOnBlankLines("2022/day11.txt"))
            assertEquals(19457438264L, day11.solvePart2())
        }
    }
}
