import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.yonatankarp.adventofcode.utils.resourceAsString
import java.io.File

fun main(args: Array<String>) {
    val year = 2022
    val day = 5

    if (args.firstOrNull() == "download") {
        readInputFileFromInternet(year, day)
    } else {
        createDayClassFile(year, day)
        createTestFile(year, day)
    }
}

fun readInputFileFromInternet(year: Int, day: Int) {
    val yearResourceDirectory = "src/test/resources/$year"
    File(yearResourceDirectory).mkdirs()

    val path = "$yearResourceDirectory/day${day.withPadding()}.txt"
    val file = File(path)
    if (file.exists()) {
        println("Input file download aborted, file already exists")
        return
    }

    val sessionCookie = resourceAsString("session_cookie.txt")
    Fuel.get("https://adventofcode.com/$year/day/$day/input")
        .header(Headers.USER_AGENT to "https://github.com/yonatankarp/advent-of-code/blob/main/src/main/kotlin/com/yonatankarp/adventofcode/Main.kt by yonatankarp")
        .header(Headers.COOKIE to "session=$sessionCookie")
        .responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    println("Failed to download input: ${response.statusCode} ${response.responseMessage}")
                }
                is Result.Success -> {
                    // Make sure to use same line separators as the system
                    result.value
                        .replace("\n", System.lineSeparator())
                        .also { File(path).writeText(it) }
                    println("$path downloaded successfully")
                }
            }
        }
        .join()
}

fun createTestFile(year: Int, day: Int) {
    val dayDirectory = "src/test/kotlin/com/yonatankarp/adventofcode/aoc$year/day${day.withPadding()}"
    createClassFile(dayDirectory, "Day${day.withPadding()}Test") { getTestCaseContent(year, day) }
}

fun createDayClassFile(year: Int, day: Int) {
    val dayDirectory = "src/main/kotlin/com/yonatankarp/adventofcode/aoc$year/day${day.withPadding()}"
    createClassFile(dayDirectory, "Day${day.withPadding()}") { getDayClassContent(year, day) }
}

private fun createClassFile(directory: String, fileName: String, classContent: () -> String) {
    File(directory).mkdirs()
    File("$directory/$fileName.kt")
        .takeIf { !it.exists() }
        ?.writeText(classContent())
        ?.also { println("$directory created") }
}

fun getTestCaseContent(year: Int, day: Int): String {
    val doubleTriple = "\"\"\"\"\"\""
    val dayWithPadding = day.withPadding()
    return """package com.yonatankarp.adventofcode.aoc$year.day${dayWithPadding}

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.yonatankarp.adventofcode.utils.resourceAsList

class Day${dayWithPadding}Test {
    private val exampleInput = $doubleTriple.trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day$dayWithPadding = Day$dayWithPadding(exampleInput)
        assertEquals(0, day$dayWithPadding.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day$dayWithPadding = Day$dayWithPadding(resourceAsList("${year}/day${dayWithPadding}.txt"))
        assertEquals(0, day$dayWithPadding.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day$dayWithPadding = Day$dayWithPadding(exampleInput)
        assertEquals(0, day$dayWithPadding.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day$dayWithPadding = Day$dayWithPadding(resourceAsList("${year}/day${dayWithPadding}.txt"))
        assertEquals(0, day$dayWithPadding.solvePart2())
    }
}""".replace("\n", System.getProperty("line.separator"))
}


fun getDayClassContent(year: Int, day: Int): String {
    return """package com.yonatankarp.adventofcode.aoc$year.day${day.withPadding()}

class Day${day.withPadding()}(input: List<String>) {

    fun solvePart1(): Int {
        return -1
    }

    fun solvePart2(): Int {
        return -1
    }
}""".replace("\n", System.getProperty("line.separator"))
}


private fun Int.withPadding(length: Int = 2) =
    this.toString().padStart(length, '0')
