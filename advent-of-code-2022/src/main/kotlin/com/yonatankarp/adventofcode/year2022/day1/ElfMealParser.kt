package com.yonatankarp.adventofcode.year2022.day1

import com.yonatankarp.adventofcode.utils.FileLoader

class ElfMealParser {
    fun parseList(fileName: String): List<Elf> {
        val elfsInput = FileLoader.readLines(fileName)
        val elfsList = mutableListOf<Elf>()

        var mealList = mutableListOf<Meal>()
        for (line in elfsInput) {
            when {
                line.isBlank() -> {
                    elfsList.add(Elf(mealList))
                    mealList = mutableListOf()
                }
                else -> mealList.add(Meal(line.toInt()))
            }
        }

        return elfsList
    }


}
