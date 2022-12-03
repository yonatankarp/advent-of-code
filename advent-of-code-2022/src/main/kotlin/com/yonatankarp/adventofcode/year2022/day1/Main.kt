package com.yonatankarp.adventofcode.year2022.day1

fun main() {
    val elfs = ElfMealParser().parseList("/day1/input.txt")
    println("Single most: ${MostCaloriesSingleElfStrategy(elfs).calculate()}")
    println("3 top: ${MostCaloriesTopThreeElfStrategy(elfs.toMutableList()).calculate()}")
}
