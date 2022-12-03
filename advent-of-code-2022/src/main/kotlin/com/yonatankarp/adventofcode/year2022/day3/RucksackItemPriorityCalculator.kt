package com.yonatankarp.adventofcode.year2022.day3

class RucksackItemPriorityCalculator {
    fun calculate(items: List<Char>) =
        items.sumOf {
            when (it) {
                in 'a'..'z' -> it - LOWER_CASE_OFFSET
                in 'A'..'Z' -> it - UPPER_CASE_OFFSET
                else -> throw RuntimeException("Item can only be lower and upper case letters")
            }
        }

    companion object {
        private const val UPPER_CASE_OFFSET = 'A' - 27 // Shift A-Z to 27-52 values
        private const val LOWER_CASE_OFFSET = 'a' - 1 // Shift a-z to 1-26 values
    }
}
