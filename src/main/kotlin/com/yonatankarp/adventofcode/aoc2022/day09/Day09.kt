package com.yonatankarp.adventofcode.aoc2022.day09

import com.yonatankarp.adventofcode.utils.Direction
import com.yonatankarp.adventofcode.utils.Position
import kotlin.math.sign

class Day09(private val input: List<String>) {

    private fun initializer(size: Int) = HashMap<Position, Char>()
        .apply {
            this[Position.POSITION_ZERO] = 's'
        } to MutableList(size) { Position.POSITION_ZERO }

    private fun simulateRope(size: Int) =
        input
            .filter { it.isNotBlank() }
            .map { it.split(" ").toDirection() }
            .fold(initializer(size)) { acc, pair ->
                val (direction, steps) = pair
                updateNewPositions(
                    direction = direction,
                    steps = steps,
                    positions = acc.first,
                    partsOfChain = acc.second,
                )
            }
            .first.keys.size

    private fun List<String>.toDirection(): Pair<Direction, Int> {
        require(this.size == 2) { "Instructions should include exactly 2 parts, but was: $this" }
        return Direction.fromChar(this[0].first()) to this[1].toInt()
    }

    private fun updateNewPositions(
        steps: Int,
        positions: HashMap<Position, Char>,
        partsOfChain: MutableList<Position>,
        direction: Direction
    ): Pair<HashMap<Position, Char>, MutableList<Position>> {
        repeat(steps) {

            // Moving the head
            partsOfChain[0] = partsOfChain.first().move(direction)
            for (i in 1 until partsOfChain.size) {
                // Moving rest of parts
                partsOfChain[i] = getNewPartPosition(
                    tail = partsOfChain[i],
                    head = partsOfChain[i - 1]
                )
            }

            positions[partsOfChain.last()] = '#'
        }

        return positions to partsOfChain
    }

    private fun getNewPartPosition(tail: Position, head: Position) =
        if (head == tail || tail in Position.allNeighbors(head, includeDiagonals = true)) tail
        else Position(
            x = tail.x + (head.x - tail.x).sign,
            y = tail.y + (head.y - tail.y).sign
        )

    fun solvePart1() = simulateRope(2)
    fun solvePart2() = simulateRope(10)
}


