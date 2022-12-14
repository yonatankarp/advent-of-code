package com.yonatankarp.adventofcode.utils

import java.io.File

internal object Resources

private val NEW_LINE = System.lineSeparator()
private val EMPTY_LINE = "$NEW_LINE$NEW_LINE"

/**
 * Read a file and return the content as a string where each line is
 * separated with the given delimiter.
 *
 * @param fileName the name of the file to read from the resources' directory.
 */
fun resourceAsString(fileName: String) =
    resourceFile(fileName).readText()

/**
 * Read the given file and return the content as a list of strings, one for each
 * line. Last trailing blank line is ignored.
 *
 * @param fileName the name of the file to read from the resources' directory.
 */
fun resourceAsList(fileName: String): List<String> = resourceFile(fileName).readLines()

/**
 * Reads the first line of text in the given file and split it on the given
 * delimiter.
 *
 * @param fileName the name of the file to read from the resources' directory.
 * @param delimiter which delimiter should be used to split the file.
 */
fun resourceAsList(fileName: String, delimiter: String = NEW_LINE): List<String> =
    resourceFile(fileName)
        .readLines()
        .first()
        .split(delimiter)

/**
 * Reads the given file and first split it on empty lines (two consecutive newlines).
 * Any single newlines in each string is represented as \n regardless of System line endings.
 *
 * @param fileName the name of the file to read from the resources' directory.
 */
fun resourceSplitOnBlankLines(fileName: String): List<String> =
    resourceAsList(fileName)
        .joinToString(NEW_LINE)
        .split(EMPTY_LINE)

/**
 * Reads the given file and returns it as a single string.
 *
 * @param fileName the name of the file to read from the resources' directory.
 */
private fun resourceFile(fileName: String) =
    File(Resources.javaClass.classLoader.getResource(fileName)!!.toURI())


