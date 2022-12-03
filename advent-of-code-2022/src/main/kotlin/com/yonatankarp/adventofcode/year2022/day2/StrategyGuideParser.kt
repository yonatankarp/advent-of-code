package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.utils.FileLoader
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS
import com.yonatankarp.adventofcode.year2022.day2.RoundEndStrategy.*

data class RoundStrategy(val opponentChoose: GameChoice, val roundEndStrategy: RoundEndStrategy)

class StrategyGuideParser {
    fun loadStrategy(fileName: String): MutableList<RoundStrategy> {
        val input = FileLoader.readLines(fileName)

        val strategy = mutableListOf<RoundStrategy>()
        for (line in input) {
            if (line.isBlank()) continue
            val (opponent, mine) = line.split(" ")
            strategy.add(
                RoundStrategy(
                    opponentChoose = toGameChoice(opponent),
                    roundEndStrategy = toRoundEndStrategy(mine)
                )
            )
        }

        return strategy
    }

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
