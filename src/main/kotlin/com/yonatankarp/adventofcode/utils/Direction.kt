package com.yonatankarp.adventofcode.utils


enum class Direction(val dx: Int, val dy: Int) {
    // Order matters due to turnRight / turnLeft implementation
    // TODO: use CyclicLinkedList here instead
    Up(dx = 0, dy = -1),
    Right(dx = 1, dy = 0),
    Down(dx = 0, dy = 1),
    Left(dx = -1, dy = 0);


    // Return a position the given amount of steps in this direction from the given position
    fun from(position: Position, steps: Int = 1) = Position(position.x + dx * steps, position.y + dy * steps)

    fun turnRight() = next()
    fun turnLeft() = prev()
    fun opposite() = next().next()

    fun turn(direction: Direction) =
        when (direction) {
            Left -> turnLeft()
            Right -> turnRight()
            else -> throw IllegalArgumentException("Not possible to turn $direction, Left and Right are the only valid directions")
        }

    companion object {
        fun fromChar(dir: Char): Direction = when (dir.uppercaseChar()) {
            in setOf('R', '>', 'E') -> Right
            in setOf('U', '^', 'N') -> Up
            in setOf('L', '<', 'W') -> Left
            in setOf('D', 'V', 'S') -> Down
            else -> throw IllegalArgumentException("Unknown direction: $dir")
        }
    }
}

fun Char.toDirection() = Direction.fromChar(this)
