package com.yonatankarp.adventofcode.aoc2022.day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList
import org.junit.jupiter.api.Nested

class Day07Test {

    private val exampleInput = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent().split("\n")

    @Nested
    inner class Part1 {
        @Test
        fun `Part 1 - Example`() {
            val day07 = Day07(exampleInput)
            assertEquals(95437, day07.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day07 = Day07(resourceAsList("2022/day07.txt"))
            assertEquals(1350966, day07.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `Part 2 - Example`() {
            val day07 = Day07(exampleInput)
            assertEquals(24933642, day07.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day07 = Day07(resourceAsList("2022/day07.txt"))
            assertEquals(6296435, day07.solvePart2())
        }
    }
}
