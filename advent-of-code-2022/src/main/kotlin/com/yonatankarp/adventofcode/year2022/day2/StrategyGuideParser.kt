package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.utils.FileLoader
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS

typealias RoundStrategy = Pair<GameChoice, GameChoice>

class StrategyGuideParser {
    fun loadStrategy(fileName: String): MutableList<RoundStrategy> {
        val input = FileLoader.readLines(fileName)

        val strategy = mutableListOf<RoundStrategy>()
        for (line in input) {
            if(line.isBlank()) continue
            val (opponent, mine) = line.split(" ")
            strategy.add(toGameChoice(opponent) to toGameChoice(mine))
        }

        return strategy
    }

    private fun toGameChoice(encryptChoice: String) =
        when (encryptChoice) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> throw RuntimeException("Unknown type $encryptChoice")
        }
}
