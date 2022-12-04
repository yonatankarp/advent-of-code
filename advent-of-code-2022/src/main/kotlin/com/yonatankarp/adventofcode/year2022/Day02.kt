package com.yonatankarp.adventofcode.year2022

import com.yonatankarp.adventofcode.utils.readPuzzleInput
import com.yonatankarp.adventofcode.year2022.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.GameChoice.SCISSORS

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

private object Scores {
    const val WIN = 6
    const val DRAW = 3
    const val LOOSE = 0
}

fun toGameChoice(line: String) = line[0].toGameChoice() to line[2].toGameChoice()


fun Char.toGameChoice(): GameChoice =
    when (this) {
        'A', 'X' -> ROCK
        'B', 'Y' -> PAPER
        'C', 'Z' -> SCISSORS
        else -> throw IllegalArgumentException("Game choice cannot be $this")
    }

private fun GameChoice.scoreAgainst(opponent: GameChoice) =
    when {
        this.win(opponent) -> Scores.WIN
        this == opponent -> Scores.DRAW
        else -> Scores.LOOSE
    }

