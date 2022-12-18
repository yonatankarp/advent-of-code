package com.yonatankarp.adventofcode.utils

import kotlin.math.abs
import kotlin.math.sign

data class Position(val x: Int, val y: Int) {
    val distanceToOrigin: Int
        get() = abs(x) + abs(y)

    /**
     * Manhattan distances to another point
     */
    fun distanceTo(other: Position) = abs(x - other.x) + abs(y - other.y)

    /**
     * Return the position at the given amount of steps in the given direction
     */
    fun move(direction: Direction, steps: Int = 1) = direction.from(this, steps)

    infix fun isAdjacentTo(other: Position): Boolean =
        (x == other.x && abs(y - other.y) == 1) ||
                (abs(x - other.x) == 1 && y == other.y)

    override fun toString() = "($x, $y)"

    operator fun times(other: Int) = Position(x * other, y * other)
    operator fun plus(other: Position) = Position(x + other.x, y + other.y)
    operator fun minus(other: Position) = Position(x - other.x, y - other.y)

    /**
     * Rotates the position around origin in the given direction and returns the result as a new position.
     * Left is counter-clockwise and right is clockwise.
     */
    fun rotate(direction: Direction): Position {
        return when (direction) {
            Direction.Left -> Position(y, -x)
            Direction.Right -> Position(-y, x)
            else -> throw IllegalArgumentException("Not possible to rotate $direction, Left and Right are the only valid directions")
        }
    }

    /**
     * Returns the sign of the position. Positive values becomes 1, negative -1 and zero stays 0.
     * Example: Pos(5,-23).sign() ==> Pos(1, -1)
     */
    fun sign() = Position(x.sign, y.sign)

    companion object {
        val POSITION_ZERO = Position(x = 0, y = 0)

        fun allDeltas(includeDiagonals: Boolean = false): List<Position> {
            val results = mutableListOf<Position>()
            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dy == 0 && dx == 0) continue
                    if (includeDiagonals || dy == 0 || dx == 0) {
                        results.add(Position(dx, dy))
                    }
                }
            }
            return results
        }

        fun allNeighbors(position: Position, includeDiagonals: Boolean = false): List<Position> =
            allDeltas(includeDiagonals)
                .map { delta ->
                    Position(position.x + delta.x, position.y + delta.y)
                }
    }
}
