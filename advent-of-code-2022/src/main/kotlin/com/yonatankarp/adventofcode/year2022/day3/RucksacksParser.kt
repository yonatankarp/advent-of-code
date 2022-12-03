package com.yonatankarp.adventofcode.year2022.day3

import com.yonatankarp.adventofcode.utils.FileLoader

class RucksacksParser {
    fun parse(fileName: String): List<Rucksack> =
        FileLoader.loadInput(fileName)
            .filter { it.isNotBlank() }
            .map {
                Rucksack(
                    Pair(
                        Compartment(it.substring(startIndex = 0, endIndex = it.length / 2)),
                        Compartment(it.substring(startIndex = (it.length / 2))),
                    )
                )
            }
            .toList()
}
