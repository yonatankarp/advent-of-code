package com.yonatankarp.adventofcode.year2022.day3

data class Rucksack(
    val compartments: Pair<Compartment, Compartment>
)

data class Compartment(private val listOfContent: String) {
    val items = listOfContent.toCharArray().toSet()
}
