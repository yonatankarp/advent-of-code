package com.yonatankarp.adventofcode.year2022.day1

class ElfMealParser {
    fun parseList(fileName: String): List<Elf> {
        val elfsInput = readFromFile(fileName)
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

    private fun readFromFile(fileName: String): List<String> =
        object {}.javaClass.getResource(fileName)!!.readText().split(System.lineSeparator())
}
