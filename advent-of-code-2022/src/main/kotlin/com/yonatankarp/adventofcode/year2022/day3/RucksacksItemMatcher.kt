package com.yonatankarp.adventofcode.year2022.day3

class RucksacksItemMatcher {
    fun match(rucksacks: List<Rucksack>): List<Char> =
        rucksacks
            .map { it.compartments.first.items.intersect(it.compartments.second.items) }
            .flatten()
}
