package utils

import com.yonatankarp.adventofcode.utils.Direction
import com.yonatankarp.adventofcode.utils.Position
import com.yonatankarp.adventofcode.utils.toDirection
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DirectionTest {
    private val left = Direction.Left
    private val right = Direction.Right
    private val up = Direction.Up
    private val down = Direction.Down

    @Test
    fun turnLeft() {
        assertEquals(left, up.turnLeft())
        assertEquals(down, left.turnLeft())
        assertEquals(right, down.turnLeft())
        assertEquals(up, right.turnLeft())
    }

    @Test
    fun turnRight() {
        assertEquals(right, up.turnRight())
        assertEquals(down, right.turnRight())
        assertEquals(left, down.turnRight())
        assertEquals(up, left.turnRight())
    }

    @Test
    fun turn() {
        assertEquals(right, up.turn(Direction.Right))
        assertEquals(down, right.turn(Direction.Right))
        assertEquals(left, down.turn(Direction.Right))
        assertEquals(up, left.turn(Direction.Right))
        assertEquals(left, up.turn(Direction.Left))
        assertEquals(down, left.turn(Direction.Left))
        assertEquals(right, down.turn(Direction.Left))
        assertEquals(up, right.turn(Direction.Left))
    }

    @Test
    fun turnUp() {
        assertThrows<IllegalArgumentException> { right.turn(Direction.Up) }
    }

    @Test
    fun turnDown() {
        assertThrows<IllegalArgumentException> { right.turn(Direction.Down) }

    }

    @Test
    fun opposite() {
        assertEquals(right, left.opposite())
        assertEquals(left, right.opposite())
        assertEquals(up, down.opposite())
        assertEquals(up, down.opposite())
    }

    @Test
    fun from() {
        assertEquals(Position(0, 7), down.from(Position(0, 0), 7))
        assertEquals(Position(-4, 3), left.from(Position(3, 3), 7))
        assertEquals(Position(10, 3), right.from(Position(3, 3), 7))
        assertEquals(Position(-3, -4), up.from(Position(-3, 3), 7))
    }

    @Test
    fun fromChar(){
        assertEquals(true, setOf('U', 'u', 'n', 'N', '^').all { it.toDirection() == Direction.Up })
        assertEquals(true, setOf('D', 'd', 's', 'S', 'v', 'V').all { it.toDirection() == Direction.Down })
        assertEquals(true, setOf('L', 'l', 'W', 'w', '<').all { it.toDirection() == Direction.Left })
        assertEquals(true, setOf('R', 'r', 'E', 'e', '>').all { it.toDirection() == Direction.Right })
    }
}
