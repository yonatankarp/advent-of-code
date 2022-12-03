package com.yonatankarp.adventofcode.year2022.day3

fun main() {
    val rucksacks = RucksacksParser().parse("/day3/input.txt")
    val matchedItems = RucksacksItemMatcher().match(rucksacks)
    val priorities = RucksackItemPriorityCalculator().calculate(matchedItems)
    println("The sum of the priorities is: $priorities")
}

