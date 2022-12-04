package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.utils.FileLoader
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS
import com.yonatankarp.adventofcode.year2022.day2.RoundEndStrategy.*

data class RoundStrategy(val opponentChoose: GameChoice, val roundEndStrategy: RoundEndStrategy)

class StrategyGuideParser {
    fun loadStrategy(fileName: String): List<RoundStrategy> =
         FileLoader.loadInput(fileName)
            .filter { it.isNotBlank() }
            .map { it.split(" ") }
            .map {
                val (opponent, mine) = it
                RoundStrategy(
                    opponentChoose = toGameChoice(opponent),
                    roundEndStrategy = toRoundEndStrategy(mine)
                )
            }
            .toList()

    private fun toGameChoice(opponentChoose: String) =
        when (opponentChoose) {
            "A" -> ROCK
            "B" -> PAPER
            "C" -> SCISSORS
            else -> throw RuntimeException("Unknown type $opponentChoose")
        }

    private fun toRoundEndStrategy(strategy: String) =
        when (strategy) {
            "X" -> LOSE
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw RuntimeException("Unknown type $strategy")
        }
}
