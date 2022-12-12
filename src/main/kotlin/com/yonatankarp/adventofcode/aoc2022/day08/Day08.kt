package com.yonatankarp.adventofcode.aoc2022.day08

import com.yonatankarp.adventofcode.utils.Grid
import com.yonatankarp.adventofcode.utils.Position

class Day08(input: List<String>) {

    private val grid = Grid.parse(input)

    private fun allPositionsFromATree(tree: Position): List<List<Position>> =
        listOf(
            (tree.y + 1 until grid.size).map { Position(tree.x, it) }, // down
            (tree.y - 1 downTo 0).map { Position(tree.x, it) }, // up
            (tree.x + 1 until grid.size).map { Position(it, tree.y) }, // right
            (tree.x - 1 downTo 0).map { Position(it, tree.y) } // left
        )

    fun solvePart1(): Int =
        grid
            .keys
            .map { it to allPositionsFromATree(it) }
            .count { treeToDirections ->
                treeToDirections.second.any { direction ->
                    // all on empty list returns true, thus tree on the edges
                    // counted as visible
                    direction.all { position ->
                        grid[treeToDirections.first] > grid[position]
                    }
                }
            }

fun solvePart2(): Int =
    grid
        .keys
        .maxOfOrNull { tree ->
            allPositionsFromATree(tree).fold(1) { acc, positions ->
                acc * positions.numberOfVisible(grid[tree])
            }
        } ?: 0

    /**
     * Return the number of trees that are visible in the given list.
     * No trees after the first tree with a height larger or equal to the
     * ownHeight is considered visible.
     */
    private fun List<Position>.numberOfVisible(ownHeight: Char): Int {
        var visible = 0
        for (tree in this) {
            val otherHeight = grid[tree]
            if (otherHeight < ownHeight) visible++
            else return visible + 1
        }
        return visible
    }
}
