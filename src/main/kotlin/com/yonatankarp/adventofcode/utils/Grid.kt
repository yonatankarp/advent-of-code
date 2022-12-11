package com.yonatankarp.adventofcode.utils

import kotlin.math.max

//@file:Suppress("KDocUnresolvedReference")

/**
 * A fixed square grid of Chars. All Chars are initialized to null when created.
 * @param size specifies the size of each side of the grid.
 * @param initializer a provider for to initialize value of the grid. If no value
 * was provided, the grid was initialized to `\u0000`.
 */
class Grid(val size: Int, initializer: () -> Char = { '\u0000' }) {
    private val data: Array<CharArray> = Array(size) { CharArray(size) { initializer() } }

    /**
     * Transformations that can be applied to a Grid.
     */
    sealed class Transformation {
        abstract class Rotation : Transformation()
        abstract class Flip : Transformation()
        object NoRotation : Rotation()
        object RotateLeft : Rotation()
        object Rotate180 : Rotation() // The same as flipping both vertically and horizontally
        object RotateRight : Rotation()
        object NoFlip : Flip()
        object FlipHorizontal : Flip()
        object FlipVertical : Flip()
    }

    /**
     * Get the value at the given position, using a Pos for addressing.
     */
    operator fun get(position: Position): Char = data[position.y][position.x]

    /**
     * Set the value at the given position, using a Pos for addressing.
     */
    operator fun set(position: Position, char: Char) {
        data[position.y][position.x] = char
    }

    /**
     * Get the row for the given y coordinate, used for Grid[y][x] addressing.
     */
    operator fun get(y: Int): CharArray = data[y]

    /**
     * Return the value at the given position, or the default if the position is outside
     * of the grid, or has not been initialized yet.
     */
    fun getOrDefault(position: Position, default: Char): Char {
        return if (position.isInGrid() && this[position] != '\u0000') this[position]
        else default
    }

    /**
     * A list of all the positions in this grid
     */
    val keys: List<Position> by lazy {
        (0 until size)
            .flatMap { y -> (0 until size)
                .map { x -> Position(x, y) } }
    }

    private fun Position.isInGrid() = (x in 0 until size) && (y in 0 until size)

    /**
     * Return the number of visible items with the given value in all directions.
     */
    fun numberOfVisibleWithValue(
        position: Position,
        value: Char,
        transparent: List<Char>,
        includeDiagonals: Boolean = false
    ): Int {
        return Position.allDeltas(includeDiagonals)
            .mapNotNull { delta ->
                generateSequence(position) {
                    Position(
                        it.x + delta.x,
                        it.y + delta.y
                    ).takeIf { next -> next.isInGrid() }
                }
                    .drop(1)
                    .firstOrNull { this[it] !in transparent }
            }
            .count { this[it] == value }
    }

    /**
     * Get all the given side of the Grid as a string
     * @param side the side to return
     * @param transformations a list of transformations that should be applied on the Grid before returning the side
     */
    fun getSide(side: Direction, transformations: List<Transformation>): String {
        val coordinates = when (side) {
            Direction.Up -> List(size) { Position(it, 0) }
            Direction.Left -> List(size) { Position(0, it) }
            Direction.Down -> List(size) { Position(it, size - 1) }
            Direction.Right -> List(size) { Position(size - 1, it) }
        }
        return coordinates
            .map { transform(it, transformations) }
            .map { this[it] }.joinToString("")
    }

    /**
     * Returns a new grid with the given transformations applied.
     */
    fun transformed(transformations: List<Transformation>): Grid {
        return subGrid(transformations, Position(0, 0), size)
    }

    /**
     * Returns a copy of this grid.
     */
    fun copy(): Grid {
        return subGrid(listOf(), Position(0, 0), size)
    }

    /**
     * Returns a new Grid with the given size that is a sub section of the current grid starting from
     * the offset (upper-left corner). The subGrid must be fully contained in this Grid.
     */
    fun subGrid(transformations: List<Transformation>, offset: Position, newSize: Int): Grid {
        val new = Grid(newSize)
        for (y in 0 until newSize) {
            for (x in 0 until newSize) {
                val transformed = transform(Position(x + offset.x, y + offset.y), transformations)
                new[y][x] = this[transformed]
            }
        }
        return new
    }

    /**
     * Count the number of occurrences of the given character in this Grid
     */
    fun count(ch: Char) = data.sumOf { row -> row.count { it == ch } }

    /**
     * Return this Grid as a string.
     * @param transformations a list of transformations to apply to the returned grid
     */
    fun toString(transformations: List<Transformation> = listOf()): String {
        return transformed(transformations).data.joinToString("\n") { it.joinToString("") }
    }

    override fun toString() = toString(listOf())

    override fun equals(other: Any?): Boolean {
        return other is Grid && data contentDeepEquals other.data
    }

    override fun hashCode(): Int {
        return data.contentDeepHashCode()
    }

    /**
     * Write all the data in the otherGrid to this grid. The other grid must fit completely within
     * this grid when starting at the given offset.
     * @param otherGrid source grid to copy data from
     * @param offset offset for where to start writing the other grid's data (top left corner)
     */
    fun writeAllWithOffset(otherGrid: Grid, offset: Position) {
        for (y in 0 until otherGrid.size) {
            for (x in 0 until otherGrid.size) {
                this[offset.y + y][offset.x + x] = otherGrid[y][x]
            }
        }
    }

    /**
     * Apply the given transformation the given position and return the resulting position.
     */
    private fun transform(position: Position, transformation: Transformation): Position {
        return when (transformation) {
            Transformation.NoRotation -> position
            Transformation.NoFlip -> position
            Transformation.RotateLeft -> Position(size - 1 - position.y, position.x)
            Transformation.Rotate180 -> Position(size - 1 - position.x, size - 1 - position.y)
            Transformation.RotateRight -> Position(position.y, size - 1 - position.x)
            Transformation.FlipHorizontal -> Position(size - 1 - position.x, position.y)
            Transformation.FlipVertical -> Position(position.x, size - 1 - position.y)
            else -> error("unreachable")
        }
    }

    /**
     * Apply all the transformations on the given position and return the resulting position.
     */
    private fun transform(position: Position, transformations: List<Transformation>): Position {
        return transformations.fold(position) { acc, trans -> transform(acc, trans) }
    }

    companion object {
        /**
         * Generates a new Grid based on the given input. If the input is not square the
         * parts not covered by the input will be left un-initialized (null characters)
         */
        fun parse(input: List<String>): Grid {
            val grid = Grid(max(input.size, input[0].length))
            for (y in input.indices) {
                for (x in input[0].indices) {
                    grid.data[y][x] = input[y][x]
                }
            }
            return grid
        }

        /**
         * Set of all possible transformations that can be applied to this Grid that would result in a different Grid.
         */
        val possibleTransformations = setOf(
            listOf(Transformation.NoRotation, Transformation.NoFlip),
            listOf(Transformation.RotateLeft, Transformation.NoFlip),
            listOf(Transformation.Rotate180, Transformation.NoFlip),
            listOf(Transformation.RotateRight, Transformation.NoFlip),
            listOf(Transformation.NoRotation, Transformation.FlipHorizontal), // Same as 180 flip vertical
            listOf(Transformation.NoRotation, Transformation.FlipVertical), // Same as 180 flip horizontal
            listOf(Transformation.RotateLeft, Transformation.FlipHorizontal), // Same as right + vertical flip
            listOf(Transformation.RotateLeft, Transformation.FlipVertical), // Same as right + horizontal flip
        )
    }
}
