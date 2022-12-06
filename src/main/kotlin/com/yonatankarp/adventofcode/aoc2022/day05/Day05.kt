package com.yonatankarp.adventofcode.aoc2022.day05

class Instruction(val amount: Int, val from: Int, val to: Int)

class Day05(input: List<String>) {

    private val stacks = parseStacks(input.first().split(System.lineSeparator()))
    private val instructions = parseInstructions(input.last().split(System.lineSeparator()))

    private fun parseStacks(input: List<String>): List<MutableList<Char>> {
        val numberOfStacks = input.last().trim().substringAfterLast(" ").toInt()
        val stacks = List(numberOfStacks) { mutableListOf<Char>() }
        input.dropLast(1) // Remove the stacks numbers
            .map { row ->
                row.chunked(4) // Each stack name is 4 chars (E.g. "[A] ")
                    .forEachIndexed { index, containerName ->
                        if (containerName.isNotBlank()) stacks[index].add(containerName[1])
                    }
            }

        return stacks
    }

    private fun parseInstructions(input: List<String>): List<Instruction> =
        input
            .map {
                val (_, amount, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex().find(it)!!.groupValues
                Instruction(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
            }

    private fun solve(isOneContainerOnly: Boolean = true) = instructions
        .forEach { instruction ->
            val toMove = MutableList(instruction.amount) { stacks[instruction.from].removeFirst() }
                .apply { if (isOneContainerOnly) this.reverse() }
            stacks[instruction.to].addAll(0, toMove)
        }
        .let { stacks.joinToString("") { it.first().toString() } }

    fun solvePart1(): String = solve(true)

    fun solvePart2(): String = solve(false)
}
