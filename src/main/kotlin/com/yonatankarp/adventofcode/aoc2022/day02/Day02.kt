package com.yonatankarp.adventofcode.aoc2022.day02

import com.yonatankarp.adventofcode.utils.readPuzzleInput
import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.PAPER
import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.ROCK
import com.yonatankarp.adventofcode.aoc2022.day02.GameChoice.SCISSORS
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.DRAW
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.LOOSE
import com.yonatankarp.adventofcode.aoc2022.day02.RoundStrategy.WIN

fun main() {
    println("Your 🪨📃✂️ score is: ${gameScore()}")
}

fun gameScore(input: List<String> = readPuzzleInput(2)) =
    input.sumOf {
        if (it.isBlank()) 0
        else {
            val (opponent, mine) = toGameChoice(it)
            mine.scoreAgainst(opponent) + mine.value
        }
    }

enum class GameChoice(val value: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun win(opponent: GameChoice): Boolean =
        when {
            this == ROCK && opponent == SCISSORS ||
                    this == PAPER && opponent == ROCK ||
                    this == SCISSORS && opponent == PAPER -> true
            else -> false
        }
}

enum class RoundStrategy {
    WIN,
    DRAW,
    LOOSE
}

private object Scores {
    const val WIN = 6
    const val DRAW = 3
    const val LOOSE = 0
}

fun toGameChoice(line: String): Pair<GameChoice, GameChoice> {
    val opponent = line[0].toGameChoice()
    val roundStrategy = line[2].toRoundStrategy()
    val mine = roundStrategy.toGameChoice(opponent)
    return opponent to mine
}

fun Char.toGameChoice(): GameChoice =
    when (this) {
        'A', 'X' -> ROCK
        'B', 'Y' -> PAPER
        'C', 'Z' -> SCISSORS
        else -> throw IllegalArgumentException("Game choice cannot be $this")
    }

fun Char.toRoundStrategy(): RoundStrategy =
    when (this) {
        'X' -> LOOSE
        'Y' -> DRAW
        'Z' -> WIN
        else -> throw IllegalArgumentException("Game choice cannot be $this")
    }

fun GameChoice.wins() =
    when(this) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
    }

fun GameChoice.looseTo() =
    when(this) {
        ROCK -> PAPER
        PAPER -> SCISSORS
        SCISSORS -> ROCK
    }

fun RoundStrategy.toGameChoice(opponent: GameChoice): GameChoice =
    when (this) {
        WIN -> opponent.looseTo()
        DRAW -> opponent
        LOOSE -> opponent.wins()
    }

private fun GameChoice.scoreAgainst(opponent: GameChoice) =
    when {
        this.win(opponent) -> Scores.WIN
        this == opponent -> Scores.DRAW
        else -> Scores.LOOSE
    }

