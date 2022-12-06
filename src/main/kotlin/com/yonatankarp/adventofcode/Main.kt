package com.yonatankarp.adventofcode

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.yonatankarp.adventofcode.utils.resourceAsString
import com.yonatankarp.adventofcode.utils.resourceFile
import io.github.furstenheim.CodeBlockStyle.FENCED
import io.github.furstenheim.CopyDown
import io.github.furstenheim.OptionsBuilder.anOptions
import java.io.File

fun main(args: Array<String>) {
    val year = 2022
    val day = 5

    if(args.firstOrNull() == "download") {
        readInputFileFromInternet(year, day)
        readPuzzleDescriptionFromInternet(year, day)
    } else {
        createDayClassFile(year, day)
        createTestFile(year, day)
    }
}

fun readInputFileFromInternet(year: Int, day: Int) {
    val path = "src/test/resources/$year/day${day.withPadding()}.txt"
    val file = File(path)
    file.parentFile.mkdirs()

    if (file.exists()) {
        println("Input file download aborted, file already exists")
        return
    }

    downloadFile("https://adventofcode.com/$year/day/$day/input", path) { File(path).writeText(it) }
}

fun readPuzzleDescriptionFromInternet(year: Int, day: Int) {
    createYearlyReadMe(year)

    val path = "${year.getYearDirectory("main")}/${day.getDayDirectory()}/README.md"
    val file = File(path)
    if (file.exists()) {
        println("Description file download aborted, file already exists")
        return
    }

    val mdOptions = anOptions()
        .withEmDelimiter("`")
        .withCodeBlockStyle(FENCED)
        .withBulletListMaker("-")
        .build()

    val response = downloadFile("https://adventofcode.com/$year/day/$day", path) {
        val content = it.substring(it.indexOf("--- Day"))
        File(path).writeText(CopyDown(mdOptions).convert(content))
    }

    val puzzleName = getPuzzleName(response)
    updateYearlyReadMe(year, day, puzzleName)
}

/**
 * Creating the README file of all puzzles of the year if one does not exist.
 */
private fun createYearlyReadMe(year: Int) {
    val yearlyReadMe = "${year.getYearDirectory("main")}/README.md"
    val yearlyFile = File(yearlyReadMe)
    if(!yearlyFile.exists()) {
        yearlyFile.parentFile.mkdirs()
        yearlyFile.createNewFile()
        yearlyFile.appendText("# Advent to Code $year" + System.lineSeparator() + System.lineSeparator())
    }
}

/**
 * Parsing the puzzle name from the puzzle content.
 * Note: Puzzle name is assumed to look like: --- Day XYZ: Some puzzle name ---
 */
private fun getPuzzleName(content: String) =
    """--- Day \d+: (.+) ---""".toRegex().find(content)!!.groups[1]!!.value

/**
 * Add the new puzzle description for the yearly README
 */
private fun updateYearlyReadMe(year: Int, day: Int, puzzleName: String) =
    File("${year.getYearDirectory("main")}/README.md")
        .appendText("- [Day $day: $puzzleName](./day${day.withPadding()}/README.md)" + System.lineSeparator())

private fun downloadFile(url: String, path: String, writeHandler: (String) -> Unit): String {
    val sessionCookie = resourceAsString("session_cookie.txt")
    return Fuel.get(url)
        .header(Headers.USER_AGENT to "https://github.com/yonatankarp/advent-of-code/blob/main/src/main/kotlin/com/yonatankarp/adventofcode/Main.kt by yonatankarp")
        .header(Headers.COOKIE to "session=$sessionCookie")
        .responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    println("Failed to download puzzle description: ${response.statusCode} ${response.responseMessage}")
                }
                is Result.Success -> {
                    // Make sure to use same line separators as the system
                    result.value
                        .replace("\n", System.lineSeparator())
                        .also { writeHandler(it) }
                    println("$path downloaded successfully")
                }
            }
        }
        .join()
        .body()
        .asString(null)
}


fun createTestFile(year: Int, day: Int) {
    val directory = "${year.getYearDirectory("test")}/${day.getDayDirectory()}/"
    createClassFile(directory, day.getTestFileName()) { getTemplateContent("test_template.txt", year, day) }
}

fun createDayClassFile(year: Int, day: Int) {
    val directory = "${year.getYearDirectory("main")}/${day.getDayDirectory()}/"
    createClassFile(directory, day.getClassFileName()) { getTemplateContent("class_template.txt", year, day) }
}

private fun createClassFile(directory: String, fileName: String, classContent: () -> String) {
    File(directory).mkdirs()
    File("$directory/$fileName.kt")
        .takeIf { !it.exists() }
        ?.writeText(classContent())
        ?.also { println("$directory created") }
}

fun getTemplateContent(templateName: String, year: Int, day: Int): String =
    resourceFile(templateName)
        .readText()
        .replace("<YEAR_PLACEHOLDER>", year.toString())
        .replace("<DAY_PLACEHOLDER>", day.withPadding())
        .replace("\n", System.getProperty("line.separator"))

private fun Int.getYearDirectory(type: String) = "src/$type/kotlin/com/yonatankarp/adventofcode/aoc$this"
private fun Int.getDayDirectory() = "day${this.withPadding()}"
private fun Int.getClassFileName() = "Day${this.withPadding()}"
private fun Int.getTestFileName() = "Day${this.withPadding()}Test"
private fun Int.withPadding(length: Int = 2) = this.toString().padStart(length, '0')
