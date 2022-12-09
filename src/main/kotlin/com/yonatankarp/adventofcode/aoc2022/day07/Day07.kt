package com.yonatankarp.adventofcode.aoc2022.day07

class Day07(input: List<String>) {

    private val directoryTree = parseInput(input)

    private fun parseInput(input: List<String>): Directory {
        val callStack = ArrayDeque<Directory>().apply { add(Directory("/")) }
        input.forEach { item ->
            when {
                item == "$ ls" -> {}
                item == "$ cd /" -> callStack.removeIf { it.name != "/" }
                item == "$ cd .." -> callStack.removeFirst()
                item.startsWith("dir") -> {
                    val (_, name) = item.split(" ")
                    callStack.first().createDirectory(name)
                }
                item.startsWith("$ cd") -> {
                    val (_, _, name) = item.split(" ")
                    callStack.addFirst(callStack.first().getDirectory(name))
                }
                else -> {
                    val (size, fileName) = item.split(" ")
                    callStack.first().addFile(File(fileName, size.toInt()))
                }
            }
        }

        return callStack.last()
    }

    fun solvePart1(): Int =
        directoryTree
            .findDirectoriesBy { it.size <= DIRECTORIES_LESS_THEN }
            .sumOf { it.size }

    fun solvePart2(): Int {
        val unusedMemory = TOTAL_FILESYSTEM_SPACE - directoryTree.size
        val missingMemory = UPDATE_REQUIRED_SPACE - unusedMemory
        return directoryTree
            .findDirectoriesBy { it.size > missingMemory }
            .minBy { it.size }.size
    }

    companion object {
        private const val DIRECTORIES_LESS_THEN = 100_000
        private const val TOTAL_FILESYSTEM_SPACE = 70_000_000
        private const val UPDATE_REQUIRED_SPACE = 30_000_000
    }

    abstract class FileSystem(val name: String) {
        abstract val size: Int
    }

    class File(name: String, override val size: Int) : FileSystem(name)

    class Directory(
        name: String,
        private val content: MutableMap<String, FileSystem> = mutableMapOf()
    ) : FileSystem(name) {
        override val size: Int
            get() = content.values.sumOf { it.size }

        fun addFile(file: File) {
            content[file.name] = file
        }

        fun getDirectory(directoryName: String) : Directory =
            content[directoryName] as Directory

        fun createDirectory(directoryName: String) {
            content[directoryName] = Directory(directoryName)
        }

        fun findDirectoriesBy(predicate: (Directory) -> Boolean): List<FileSystem> {
            val directories = content
                .values
                .toList()
                .filterIsInstance<Directory>()
            return directories.filter { predicate(it) } +
                    directories.flatMap { it.findDirectoriesBy(predicate) }
        }
    }
}
