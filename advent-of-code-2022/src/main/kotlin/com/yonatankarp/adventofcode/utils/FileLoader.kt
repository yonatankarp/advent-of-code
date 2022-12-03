package com.yonatankarp.adventofcode.utils

object FileLoader {
    fun loadInput(fileName: String): List<String> =
        object {}.javaClass
            .getResource(fileName)!!
            .readText()
            .split(System.lineSeparator())
}
