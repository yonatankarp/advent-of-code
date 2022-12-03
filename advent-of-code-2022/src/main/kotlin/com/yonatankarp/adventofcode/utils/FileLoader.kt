package com.yonatankarp.adventofcode.utils

object FileLoader {
    fun readLines(fileName: String): List<String> =
        object {}.javaClass.getResource(fileName)!!.readText().split(System.lineSeparator())
}
