package com.yonatankarp.adventofcode.year2022.day1

abstract class MostCaloriesStrategy {
    abstract fun calculate(): Int
}

class MostCaloriesSingleElfStrategy(private val elfs: List<Elf>) : MostCaloriesStrategy() {
    override fun calculate() = elfs.maxBy { it.totalCalories }.totalCalories
}

class MostCaloriesTopThreeElfStrategy(private val elfs: MutableList<Elf>) : MostCaloriesStrategy() {
    override fun calculate(): Int {
        var sum = 0
        for(i in 1..3) {
            val topElf = elfs.maxBy { it.totalCalories }
            sum += topElf.totalCalories
            elfs.remove(topElf)
        }

        return sum
    }
}
