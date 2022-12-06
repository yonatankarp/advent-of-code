package com.yonatankarp.adventofcode.aoc2022.day05

import com.yonatankarp.adventofcode.utils.resourceSplitOnBlankLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Disabled
class Day05Test {

    private val exampleInput = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent().split("\n\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day05 = Day05(exampleInput)
            assertEquals("CMZ", day05.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day05 = Day05(resourceSplitOnBlankLines("2022/day05.txt"))
            assertEquals("JCMHLVGMG", day05.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day05 = Day05(exampleInput)
            assertEquals("", day05.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day05 = Day05(resourceSplitOnBlankLines("2022/day05.txt"))
            assertEquals("", day05.solvePart2())
        }
    }
}
