package com.yonatankarp.adventofcode.year2022.day1

data class Elf(val items: List<Meal>) {
    val totalCalories = items.fold(0) { acc, meal -> acc + meal.calories  }
}
