package com.yonatankarp.adventofcode.utils

/**
 * Returns true if this string has duplicated characters in it, and false otherwise.
 *
 * @param ignoreCases - indicates whether lower and upper case string should be
 *                      treated as the same character
 */
fun String.hasDuplicates(ignoreCases: Boolean = true): Boolean {
    val string = if (ignoreCases) this.lowercase() else this
    string.asSequence()
        .groupingBy { it }
        .aggregate { _, _: Int?, _, first ->
            if (first) 1
            else return true
        }
        .let { return false }
}
