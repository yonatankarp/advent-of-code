package com.yonatankarp.adventofcode.year2022.day2

fun main() {
    val strategy = StrategyGuideParser().loadStrategy("/day02/input.txt")
    val result = GameResultCalculator().calculate(strategy)
    println(result)
}
