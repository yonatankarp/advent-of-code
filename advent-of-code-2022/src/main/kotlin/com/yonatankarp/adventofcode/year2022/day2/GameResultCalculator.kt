package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS

class GameResultCalculator {
    private val rockStrategy = RockStrategy()
    private val paperStrategy = PaperStrategy()
    private val scissorsStrategy = ScissorsStrategy()

    fun calculate(game: List<RoundStrategy>) = game.sumOf { roundScore(it) }

    private fun roundScore(round: RoundStrategy) =
        when (round.opponentChoose) {
            ROCK -> rockStrategy.roundResult(round.roundEndStrategy)
            PAPER -> paperStrategy.roundResult(round.roundEndStrategy)
            SCISSORS -> scissorsStrategy.roundResult(round.roundEndStrategy)
        }
}
