package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS

class GameResultCalculator {
    fun calculate(game: List<RoundStrategy>): Int {
        var sum = 0
        game.forEach{

            print("Round: $it - ")
            val score = roundScore(it)
            sum += score
            println(" - ($score) - total score: $sum")

        }

        return sum
    }

    private fun roundScore(round: RoundStrategy) =
        when {
            // Win
            round.first == ROCK && round.second == PAPER -> win(PAPER)
            round.first == PAPER && round.second == SCISSORS -> win(SCISSORS)
            round.first == SCISSORS && round.second == ROCK -> win(ROCK)
            // Tie
            round.first == ROCK && round.second == ROCK -> tie(ROCK)
            round.first == PAPER && round.second == PAPER -> tie(PAPER)
            round.first == SCISSORS && round.second == SCISSORS -> tie(SCISSORS)
            // Lose
            round.first == PAPER && round.second == ROCK -> lose(ROCK)
            round.first == ROCK && round.second == SCISSORS -> lose(SCISSORS)
            round.first == SCISSORS && round.second == PAPER -> lose(PAPER)

            else -> throw RuntimeException("Combo unknown: $round")
        }

    private fun win(choice: GameChoice) =
        (GAME_WIN_SCORE + choice.score).also { print("You won the round! 🎉") }

    private fun tie(choice: GameChoice) =
        (GAME_TIE_SCORE + choice.score).also { print("It's a tie... 🤷‍") }

    private fun lose(choice: GameChoice) =
        (GAME_LOSE_SCORE + choice.score).also { print("You lost 😢") }

    companion object {
        private const val GAME_WIN_SCORE = 6
        private const val GAME_TIE_SCORE = 3
        private const val GAME_LOSE_SCORE = 0
    }
}
