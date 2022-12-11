package utils

import com.yonatankarp.adventofcode.utils.Direction
import com.yonatankarp.adventofcode.utils.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun distanceToOrigin() {
        assertEquals(0, Position(0, 0).distanceToOrigin)
        assertEquals(10, Position(-5, -5).distanceToOrigin)
        assertEquals(10, Position(5, -5).distanceToOrigin)
        assertEquals(10, Position(-5, 5).distanceToOrigin)
        assertEquals(10, Position(5, 5).distanceToOrigin)
    }

    @Test
    fun distanceTo() {
        assertEquals(0, Position(0, 0).distanceTo(Position(0, 0)))
        assertEquals(20, Position(5, 5).distanceTo(Position(-5, -5)))
        assertEquals(10, Position(5, -5).distanceTo(Position(-5, -5)))
    }

    @Test
    fun move() {
        assertEquals(Position(0, 7), Position(0, 0).move(Direction.Down, 7))
        assertEquals(Position(-4, 3), Position(3, 3).move(Direction.Left, 7))
        assertEquals(Position(10, 3), Position(3, 3).move(Direction.Right, 7))
        assertEquals(Position(-3, -4), Position(-3, 3).move(Direction.Up, 7))
    }

    @Test
    fun plus() {
        assertEquals(Position(5, 5), Position(5, 0) + Position(0, 5))
        assertEquals(Position(5, 5), Position(0, 0) + Position(5, 5))
        assertEquals(Position(5, 5), Position(10, 10) + Position(-5, -5))
    }

    @Test
    fun times() {
        assertEquals(Position(5, 5), Position(1, 1) * 5)
        assertEquals(Position(5, 5), Position(-5, -5) * -1)
        assertEquals(Position(0, 0), Position(10, 10) * 0)
    }

    @Test
    fun rotate() {
        assertEquals(Position(1, -1), Position(1, 1).rotate(Direction.Left))
        assertEquals(Position(-10, -10), Position(10, -10).rotate(Direction.Left))
        assertEquals(Position(-19, 1), Position(-1, -19).rotate(Direction.Left))
        assertEquals(Position(1, 21), Position(-21, 1).rotate(Direction.Left))

        assertEquals(Position(1, 16), Position(16, -1).rotate(Direction.Right))
        assertEquals(Position(91, -12), Position(-12, -91).rotate(Direction.Right))
        assertEquals(Position(-61, -16), Position(-16, 61).rotate(Direction.Right))
        assertEquals(Position(-1, 1), Position(1, 1).rotate(Direction.Right))
    }
}
