package utils

import com.yonatankarp.adventofcode.utils.Grid
import com.yonatankarp.adventofcode.utils.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GridTest {
    @Test
    fun `rotate left`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val expected = """
                3cC
                2bB
                1aA
            """.trimIndent()
        assertEquals(expected, grid.toString(listOf(Grid.Transformation.RotateLeft)))
    }

    @Test
    fun `rotate right`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val expected = """
            Aa1
            Bb2
            Cc3
        """.trimIndent()
        assertEquals(expected, grid.toString(listOf(Grid.Transformation.RotateRight)))
    }

    @Test
    fun `rotate 180`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val expected = """
            CBA
            cba
            321
        """.trimIndent()
        assertEquals(expected, grid.toString(listOf(Grid.Transformation.Rotate180)))
    }

    @Test
    fun `flip horizontal`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val expected = """
            321
            cba
            CBA
        """.trimIndent()
        assertEquals(expected, grid.toString(listOf(Grid.Transformation.FlipHorizontal)))
    }

    @Test
    fun `flip vertical`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val expected = """
            ABC
            abc
            123
        """.trimIndent()
        assertEquals(expected, grid.toString(listOf(Grid.Transformation.FlipVertical)))
    }

    @Test
    fun `all possible transformations`() {
        val grid = Grid.parse("""
            123
            abc
            ABC
        """.trimIndent().split("\n"))
        val all = Grid.possibleTransformations.map { grid.toString(it) to it }.sortedBy { it.first }
        assertEquals(8, all.size)
        assertEquals(all.size, all.map { it.first }.toSet().size)
    }

    @Test
    fun `number of visible from all seen with diagonals`() {
        val grid = Grid.parse("""
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent().split("\n"))
        assertEquals(8, grid.numberOfVisibleWithValue(Position(3, 4), '#', listOf('.'), true))
    }

    @Test
    fun `number of visible from all seen without diagonals`() {
        val grid = Grid.parse("""
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent().split("\n"))
        assertEquals(4, grid.numberOfVisibleWithValue(Position(3, 4), '#', listOf('.'), false))
    }

    @Test
    fun `number of visible blocking none transparent`() {
        val grid = Grid.parse("""
            .............
            .L.L.#.#.#.#.
            .............
        """.trimIndent().split("\n"))
        assertEquals(0, grid.numberOfVisibleWithValue(Position(1, 1), '#', listOf('.'), true))
    }

    @Test
    fun `number of visibles when none is visible`() {
        val grid = Grid.parse("""
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent().split("\n"))
        assertEquals(0, grid.numberOfVisibleWithValue(Position(3, 3), '#', listOf('.'), true))
    }
}
