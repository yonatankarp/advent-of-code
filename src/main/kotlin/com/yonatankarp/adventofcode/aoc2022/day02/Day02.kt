package com.yonatankarp.adventofcode.aoc2022.day02

import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.PAPER
import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.ROCK
import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.SCISSORS
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.DRAW
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.LOSE
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.WIN

enum class GameChoice(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun wins(opponent: GameChoice) =
        this == ROCK && opponent == SCISSORS ||
                this == PAPER && opponent == ROCK ||
                this == SCISSORS && opponent == PAPER
}

enum class RoundStrategy(val score: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0)
}

class Day02(private val input: List<String>) {

    fun solvePart1() =
        input.sumOf {
            if (it.isBlank()) 0
            else {
                val (opponent, mine) = toGameChoicePart1(it)
                mine.scoreAgainst(opponent).score + mine.score
            }
        }

    private fun toGameChoicePart1(line: String): Pair<GameChoice, GameChoice> {
        val opponent = line[0].toGameChoicePart1()
        val mine = line[2].toGameChoicePart1()
        return opponent to mine
    }

    private fun Char.toGameChoicePart1(): GameChoice =
        when (this) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSORS
            else -> throw IllegalArgumentException("Game choice cannot be $this")
        }

    fun solvePart2() =
        input.sumOf {
            if (it.isBlank()) 0
            else {
                val (opponent, mine) = toGameChoicePart2(it)
                mine.scoreAgainst(opponent).score + mine.score
            }
        }

    private fun toGameChoicePart2(line: String): Pair<GameChoice, GameChoice> {
        val opponent = line[0].toGameChoicePart2()
        val roundStrategy = line[2].toRoundStrategy()
        val mine = roundStrategy.toGameChoicePart2(opponent)
        return opponent to mine
    }

    private fun Char.toGameChoicePart2(): GameChoice =
        when (this) {
            'A' -> ROCK
            'B' -> PAPER
            'C' -> SCISSORS
            else -> throw IllegalArgumentException("Game choice cannot be $this")
        }

    private fun Char.toRoundStrategy(): RoundStrategy =
        when (this) {
            'X' -> LOSE
            'Y' -> DRAW
            'Z' -> WIN
            else -> throw IllegalArgumentException("Game choice cannot be $this")
        }

    private fun GameChoice.wins() =
        when (this) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        }

    private fun GameChoice.loseTo() =
        when (this) {
            ROCK -> PAPER
            PAPER -> SCISSORS
            SCISSORS -> ROCK
        }

    private fun RoundStrategy.toGameChoicePart2(opponent: GameChoice): GameChoice =
        when (this) {
            WIN -> opponent.loseTo()
            DRAW -> opponent
            LOSE -> opponent.wins()
        }

    private fun GameChoice.scoreAgainst(opponent: GameChoice) =
        when {
            this.wins(opponent) -> WIN
            this == opponent -> DRAW
            else -> LOSE
        }
}
